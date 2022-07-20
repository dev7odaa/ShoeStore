package com.example.shoestore.ui.fragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailBinding
import com.example.shoestore.databinding.FragmentShoeListBinding
import com.example.shoestore.ui.viewmodel.ShoeViewModel
import com.google.android.material.snackbar.Snackbar

class ShoeDetailFragment : Fragment() {
    private val viewModel: ShoeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        viewModel.resetNewShoe()
        binding.viewModel = viewModel


        binding.btnSave.setOnClickListener {

            if (viewModel.addNewShoe()) {

                it.findNavController().navigate(
                    ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
                )

            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.enter_all_fields),
                    Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnCancel.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }

        return binding.root
    }

}