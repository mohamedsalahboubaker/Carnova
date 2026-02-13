package com.salah.carnova.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.salah.carnova.domain.CategoryModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CategoryViewModel: ViewModel() {
    private val _categories= mutableStateOf<List<CategoryModel>>(emptyList())

    val categories:State<List<CategoryModel>> = _categories

    private val _isLoading=mutableStateOf(true)

    val isLoading: State<Boolean> = _isLoading

    init {
        loadCategories()
    }

    private fun loadCategories(){
        val database= FirebaseDatabase.getInstance()
        val ref=database.getReference("Category")

        ref.addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
               val list= ArrayList<CategoryModel>()
                snapshot.children.forEach {
                    it.getValue(CategoryModel::class.java)?.let{item->list.add(item)}

                    _categories.value=list
                    _isLoading.value=false
                }

            }

            override fun onCancelled(error: DatabaseError) {
                _isLoading.value=false
            }

        })
    }






}