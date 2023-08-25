package com.example.test01.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.test01.Weather.WeatherRepository
import com.example.test01.Weather.WeatherResponse
import com.example.test01.databinding.FragmentSlideshowBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

/*        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val weather = getWeather()
                //将天气信息的个个值进行整理
                val weatherString = buildString {
                    append("城市: ${weather.city}\n")
                    append("日期: ${weather.date}\n")
                    append("星期: ${weather.week}\n")
                    append("天气: ${weather.wea}\n")
                    append("温度: ${weather.tem}\n")
                    append("风向: ${weather.win}\n")
                    append("风速: ${weather.win_speed}\n")
                    append("空气质量: ${weather.air}\n")
                    append("气压: ${weather.pressure}\n")
                    append("湿度: ${weather.humidity}")
                }
                //withContext协程函数，用于在主线程上运行代码，打印显示信息
                withContext(Dispatchers.Main) {
                    binding.textSlideshow.text = weatherString
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.textSlideshow.text = "Error: ${e.message}"
                }
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //将获取天气信息的详细网址传递过来
    private suspend fun getWeather(): WeatherResponse {
        return WeatherRepository().getWeather()
    }

}