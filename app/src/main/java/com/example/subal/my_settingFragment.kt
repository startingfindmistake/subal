package com.example.subal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.text.TextUtils
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class MySettingFragment : PreferenceFragmentCompat() {
    //onCreatePreferences 메서드는 환경설정 화면을 생성할 때 호출된다
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //XML리소스에서 환경설정을 로드한다.
        setPreferencesFromResource(R.xml.settings, rootKey)

        //EditTextPreference와 ListPreference를 찾아 변수에 할당한다.
        val idPreference: EditTextPreference? = findPreference("id")
        val colorPreference: ListPreference? = findPreference("color")

        //colorPreference의 요약 정보를 설정한다
        colorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
        //idPreference의 요약 정보를 설정한다
        idPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference>{ preference ->
                val text = preference.text
                //입력된 텍스트가 없으면 "설정이 되지 않았습니다." 를 반환하고,
                //그렇지 않으면 "설정된 id값은 $text 입니다"를 반환한다.
                if(TextUtils.isEmpty(text)){
                    "설정이 되지 않았습니다."
                }else {
                    "설정된 id 값은 $text 입니다. "
                }
            }
    }
}















//기본 값
/*
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [my_settingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class my_settingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_setting, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment my_settingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            my_settingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

 */