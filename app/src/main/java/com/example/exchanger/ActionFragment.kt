package com.example.exchanger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exchanger.databinding.FragmentActionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActionFragment : Fragment() {

    private lateinit var binding: FragmentActionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActionBinding.inflate(inflater, container, false)
        return binding.root
    }
}