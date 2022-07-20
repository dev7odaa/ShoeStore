package com.example.shoestore.ui.fragments.ui

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeListBinding
import com.example.shoestore.ui.viewmodel.ShoeViewModel


class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
    private val binding by lazy {
        FragmentShoeListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.shoeList.observe(viewLifecycleOwner) {
            binding.shoeListLinear.removeAllViews()
            for (shoe in it) {
                addShoe(shoe.name)
            }
        }

        binding.btnAddShoe.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_logout ->
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())


        }
        return super.onOptionsItemSelected(item)
    }

    private fun addShoe(shoeName: String) {
        var view = layoutInflater.inflate(R.layout.item_shoe, null)
        view.findViewById<TextView>(R.id.textView).text = shoeName
        binding.shoeListLinear.addView(view)
    }

}