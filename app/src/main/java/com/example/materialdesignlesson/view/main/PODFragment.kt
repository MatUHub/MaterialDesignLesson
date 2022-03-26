package com.example.materialdesignlesson.view.main

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.LineBackgroundSpan
import android.text.style.QuoteSpan
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesignlesson.R
import com.example.materialdesignlesson.databinding.FragmentMainBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.example.materialdesignlesson.view.chips.SettingsFragment
import com.example.materialdesignlesson.viewmodel.PODData
import com.example.materialdesignlesson.viewmodel.PODViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior


class PODFragment() : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private var dataPOD: Int = 1

    constructor(version: Int) : this() {
        dataPOD = version
    }

    //инициализация viewModel
    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }

    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendRequest(0)
        //активация слушателя по нажатию кнопки (W)
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                //спарсили ссылку на сайт Wikipedia
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
        //задание настроек для bottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        // открытие bottomSheet на половину


        //фиксированные состояния bottomSheet
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> myToast("состояние DRAGGING")
                    BottomSheetBehavior.STATE_COLLAPSED -> myToast("состояние COLLAPSED")
                    BottomSheetBehavior.STATE_EXPANDED -> myToast("состояние EXPANDED")
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> myToast("состояние HALF_EXPANDED")
                    BottomSheetBehavior.STATE_HIDDEN -> myToast("состояние HIDDEN")
                    BottomSheetBehavior.STATE_SETTLING -> myToast("состояние SETTLING")
                }
            }

            //указывает уровень открытия bottomSheet через slideOffset
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("log", "slide $slideOffset")

            }
        })
        //связка ActionBar Activity с bottomAppBar
        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {
            if (isMain) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            isMain = !isMain
        }

        var flag = false

        binding.imageView.setOnClickListener {
            flag = !flag


            val changeImageTransform = ChangeImageTransform()
            changeImageTransform.duration = 3000
            TransitionManager.beginDelayedTransition(binding.transitionsContainer, changeImageTransform)
            if (flag) {
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }


        }
    }

    var isMain = true

    @SuppressLint("ResourceAsColor")
    private fun renderData(podData: PODData) {
        when (podData) {
            is PODData.Error -> {
                myToast("Ошибка получения данных")
            }
            is PODData.Loading -> {
                myToast("Загрузка данных")
            }
            is PODData.Success -> {
                when (dataPOD) {
                    0 -> {  //добавление заголовка и описания картинки с сайта nasa


                        binding.included.bottomSheetDescriptionHeader.text =
                            podData.serverResponse.title
                        binding.included.bottomSheetDescription.text =
                            podData.serverResponse.explanation

                        val spannableMutable = SpannableStringBuilder(binding.included.bottomSheetDescription.text)

                        for(i in spannableMutable.indices){
                            if(spannableMutable[i] == 'i') {
                                spannableMutable.setSpan(
                                    ImageSpan(
                                        requireContext(),
                                        R.drawable.ic_info
                                    ), i, i+1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                                )
                            }
                        }



                        spannableMutable.setSpan(ForegroundColorSpan(Color.RED), 0, spannableMutable.length/3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                        spannableMutable.setSpan(ForegroundColorSpan(Color.GREEN), spannableMutable.length/3, spannableMutable.length * 2/3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                        spannableMutable.setSpan(ForegroundColorSpan(Color.YELLOW), spannableMutable.length * 2/3,spannableMutable.length - 1 , Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

                        binding.included.bottomSheetDescription.text = spannableMutable

                        binding.imageView.load(podData.serverResponse.url) {
                            placeholder(R.drawable.ic_no_photo_vector)


                        }
                    }
                    1 -> {
                        viewModel.sendRequest(-1)
                        binding.included.bottomSheetDescriptionHeader.text =
                            podData.serverResponse.title
                        binding.included.bottomSheetDescription.text =
                            podData.serverResponse.explanation

                        binding.imageView.load(podData.serverResponse.url) {
                            placeholder(R.drawable.ic_no_photo_vector)

                        }
                    }

                }

            }
        }
    }

    //создание menu_bottom_bar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    //создание слушателей на кнопки меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> {
                myToast("нажатие кнопки app_bar_fav")
            }
            R.id.app_bar_settings -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, SettingsFragment.newInstance()).addToBackStack("")
                    .commit()
            }
            //для поиска кнопки бургер используется android.R.id.home
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager, "a")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = PODFragment(1)
    }

    fun myToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}
