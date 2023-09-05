package com.example.testeppie.sqlite

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testeppie.R

class SqliteActivity : AppCompatActivity() {

    private lateinit var et_name: EditText
    private lateinit var et_age: EditText
    private lateinit var btn_add: Button
    private lateinit var btn_deleteAll: Button
    private lateinit var sw_activeUser: Switch
    private lateinit var lv_userList: ListView

    private lateinit var userArrayAdapter: ArrayAdapter<UserModel>
    private lateinit var dataBaseHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        et_name = findViewById(R.id.et_name)
        et_age = findViewById(R.id.et_age)
        btn_add = findViewById(R.id.btn_add)
        btn_deleteAll = findViewById(R.id.btn_deleteAll)
        sw_activeUser = findViewById(R.id.sw_active)
        lv_userList = findViewById(R.id.lv_userList)

        dataBaseHelper = DataBaseHelper(this@SqliteActivity)
        updateListView(dataBaseHelper)
        var userModel: UserModel?

        btn_add.setOnClickListener {
            userModel = try {
                UserModel(-1, et_name.text.toString(), et_age.text.toString().toInt(), sw_activeUser.isChecked)
            } catch (e: Exception) {
                //Toast.makeText(this@SqliteActivity, "Invalid format", Toast.LENGTH_SHORT).show()
                UserModel(-1, "Error", 0, false)
            }

            val dataBaseHelper = DataBaseHelper(this@SqliteActivity)
            val success: Boolean = dataBaseHelper.addUser(userModel)
            //Toast.makeText(this@SqliteActivity, "Success = $success", Toast.LENGTH_SHORT).show()

            updateListView(dataBaseHelper)
        }

        btn_deleteAll.setOnClickListener {
            dataBaseHelper = DataBaseHelper(this@SqliteActivity)
            dataBaseHelper.clearDb()
            updateListView(dataBaseHelper)
        }

        lv_userList.setOnItemClickListener { parent, _, position, _ ->
            val selectedUser = parent.getItemAtPosition(position) as UserModel
            dataBaseHelper.deleteUser(selectedUser)
            updateListView(dataBaseHelper)
            Toast.makeText(this@SqliteActivity, "Deleted $selectedUser", Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateListView(dataBaseHelper: DataBaseHelper) {
        userArrayAdapter = ArrayAdapter<UserModel>(
            this@SqliteActivity,
            android.R.layout.simple_list_item_1,
            dataBaseHelper.allUsers
        )
        lv_userList.adapter = userArrayAdapter
    }
}