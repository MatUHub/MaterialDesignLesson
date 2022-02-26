package com.example.materialdesignlesson.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
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


class PODFragment (): BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private var dataPOD: Int = 1

    constructor(version : Int): this(){
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
        viewModel.sendRequest()
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

        binding.fab.setOnClickListener{
           if(isMain){
               bottomSheetBehavior.state=BottomSheetBehavior.STATE_EXPANDED
           } else {
               bottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
           }
            isMain = !isMain
        }
    }
    var isMain = true

    private fun renderData(podData: PODData) {
        when (podData) {
            is PODData.Error -> {
                myToast("Ошибка получения данных")
            }
            is PODData.Loading -> {
                myToast("Загрузка данных")
            }
            is PODData.Success -> {
                when(dataPOD){
                    1 -> {  //добавление заголовка и описания картинки с сайта nasa
                        binding.included.bottomSheetDescriptionHeader.text = podData.serverResponse.title
                        binding.included.bottomSheetDescription.text = podData.serverResponse.explanation

                        binding.imageView.load(podData.serverResponse.url) {
                            placeholder(R.drawable.ic_no_photo_vector)

                        }}
                    2 -> { binding.included.bottomSheetDescriptionHeader.text = podData.serverResponse.title
                        binding.included.bottomSheetDescription.text = podData.serverResponse.explanation

                        binding.imageView.load(podData.serverResponse.url) {
                            placeholder(R.drawable.ic_no_photo_vector)

                        }}
                    3 -> {binding.included.bottomSheetDescriptionHeader.text = podData.serverResponse.title
                        binding.included.bottomSheetDescription.text = podData.serverResponse.explanation

                        binding.imageView.load(podData.serverResponse.url) {
                            placeholder(R.drawable.ic_no_photo_vector)

                        }}
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
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.mainContainer, SettingsFragment.newInstance()).addToBackStack("").commit()
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
