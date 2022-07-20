package com.example.shoestore.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.model.ShoeDetails

class ShoeViewModel: ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<ShoeDetails>>()
    val shoeList: LiveData<MutableList<ShoeDetails>>
        get() = _shoeList

    var newShoeName = ""
    var newShoeSize = ""
    var newShoeCompany = ""
    var newShoeDescription = ""

    init {
        _shoeList.value = mutableListOf()
        mockData()
    }

    fun resetNewShoe() {
        newShoeName = ""
        newShoeCompany = ""
        newShoeSize = ""
        newShoeDescription = ""
    }

    fun addNewShoe(): Boolean {

        if (!validateNewShoeInputs()) return false

        addShoe(
            newShoeName,
            newShoeCompany,
            newShoeSize.toDouble(),
            newShoeDescription
        )

        return true
    }

    private fun mockData() {
        var count = 1
        while (count <= 3) {
            addShoe("Shoe $count", "Company $count", 7.0, "Shoe Desc $count")
            ++count
        }
    }

    private fun addShoe(name: String, company: String, size: Double, description: String) {
        val shoe = ShoeDetails(name, company, size, description)
        _shoeList.value!!.add(shoe)


    }

    private fun validateNewShoeInputs(): Boolean {

        if (newShoeName.isBlank() ||
            newShoeCompany.isBlank() ||
            newShoeSize.isBlank()  ||
            newShoeDescription.isBlank()) {
            return false
        }

        return true
    }
}