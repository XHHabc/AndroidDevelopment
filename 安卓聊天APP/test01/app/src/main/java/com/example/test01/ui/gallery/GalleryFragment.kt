package com.example.test01.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test01.Friends.FriendAdapter
import com.example.test01.R
import com.example.test01.databinding.FragmentGalleryBinding
import android.app.AlertDialog
import android.widget.EditText
import com.example.test01.Friends.Friend

class GalleryFragment : Fragment() {
    private lateinit var addButton: Button
    private var _binding: FragmentGalleryBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
/*        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        val RView: RecyclerView = binding.recyclerView02
        RView.layoutManager = LinearLayoutManager(context)
        val adapter = FriendAdapter(galleryViewModel.listFriend)
        RView.adapter = adapter


        addButton = binding.addButton

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton.setOnClickListener {
            // 执行添加好友的操作
           //Toast.makeText(context, "添加好友", Toast.LENGTH_SHORT).show()
            val editText = EditText(requireContext())
            AlertDialog.Builder(requireContext())
                .setTitle("添加好友")
                .setMessage("请输入好友名字")
                .setView(editText)
                .setPositiveButton("确定") { _, _ ->
                    val friendName = editText.text.toString()
                    // 执行添加好友的操作，结合GalleryViewModel里创建的ArrayList利用add方法以Friend对象为参数添加进listFriend中
                    val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
                    galleryViewModel.listFriend.add(Friend(R.drawable.haoyou,"$friendName"))
                    //Toast.makeText(context, "添加好友: $friendName", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("取消", null)
                .show()
        }

        // 设置RecyclerView的适配器
        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        val adapter = FriendAdapter(galleryViewModel.listFriend)
        binding.recyclerView02.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}