package com.salah.carnova.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.salah.carnova.domain.CarModel

class CarViewModel: ViewModel() {
    private val _cars= mutableStateOf<List<CarModel>>(emptyList())
    val cars: State<List<CarModel>> = _cars
    private val _isLoading=mutableStateOf (true)
    val isLoading:State<Boolean> = _isLoading


    init{
        fetchCars()
    }

    private fun fetchCars(){
        val ref= FirebaseDatabase.getInstance().getReference("Cars")

        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val temp=mutableListOf<CarModel>()
                for (child in snapshot.children){
                    child.getValue(CarModel::class.java)?.let {
                        temp.add(it)
                    }

                }
                _cars.value=temp
                _isLoading.value=false
            }

            override fun onCancelled(error: DatabaseError) {
                _isLoading.value=false
            }

        })

    }


}