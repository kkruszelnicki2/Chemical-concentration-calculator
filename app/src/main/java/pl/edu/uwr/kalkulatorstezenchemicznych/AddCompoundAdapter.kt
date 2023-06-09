package pl.edu.uwr.kalkulatorstezenchemicznych

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddCompoundAdapter(
    private val context: Context,
    private var compoundList: MutableList<Compound>,
) : RecyclerView.Adapter<AddCompoundAdapter.AddCompoundViewHolder>() {

    private val TASK_LIST = "tasks"
    private val TASK_FILE = "task_file"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddCompoundAdapter.AddCompoundViewHolder {
        return AddCompoundViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rv_item_add,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: AddCompoundAdapter.AddCompoundViewHolder, position: Int) {
        val name = compoundList[position].name
        val symbol = compoundList[position].symbol
        val moleMass = compoundList[position].molemass

        holder.compoundName.text = name

        val bundle = Bundle()
        bundle.putString("name",name)
        bundle.putString("symbol",symbol)
        bundle.putDouble("moleMass",moleMass)

        holder.addButton.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.to_menu,bundle)
        )
    }

    override fun getItemCount() = compoundList.size

    class AddCompoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val compoundName: TextView = itemView.findViewById(R.id.name)
        val addButton: ImageView = itemView.findViewById(R.id.add)
    }

    fun saveCompoundList(context: Context) {
        val json = Gson().toJson(compoundList)
        val sharedPreferences = context.getSharedPreferences(TASK_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(TASK_LIST, json).apply()
    }

    fun getCompoundList(context: Context) {
        val sharedPreferences = context.getSharedPreferences(TASK_FILE, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(TASK_LIST, "{}")

        val type = object : TypeToken<ArrayList<Compound>>() {}.type
        compoundList = Gson().fromJson(json, type)
    }

}
