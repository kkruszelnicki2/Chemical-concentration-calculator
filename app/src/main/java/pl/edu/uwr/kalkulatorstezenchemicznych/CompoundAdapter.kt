package pl.edu.uwr.kalkulatorstezenchemicznych

import android.content.Context
import android.graphics.Color.GREEN
import android.graphics.Color.WHITE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CompoundAdapter(
    private val context: Context,
    private var compoundList: MutableList<Compound>,
) : RecyclerView.Adapter<CompoundAdapter.CompoundViewHolder>() {

    private val TASK_LIST = "tasks"
    private val TASK_FILE = "task_file"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompoundAdapter.CompoundViewHolder {
        return CompoundViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rv_item,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: CompoundAdapter.CompoundViewHolder, position: Int) {
        val name = compoundList[position].name
        val symbol = compoundList[position].symbol
        val moleMass = compoundList[position].molemass

        holder.compoundName.text = name
        holder.compoundSymbol.text = symbol

        val bundle = Bundle()
        bundle.putString("name",name)
        bundle.putDouble("moleMass",moleMass)

        holder.compoundLayout.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.to_calculator,bundle)
        )
    }

    override fun getItemCount() = compoundList.size

    class CompoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val compoundName: TextView = itemView.findViewById(R.id.name)
        val compoundSymbol: TextView = itemView.findViewById(R.id.symbol)
        val compoundLayout: LinearLayout = itemView.findViewById(R.id.compLayout)
    }

    fun addToList(name: String, symbol: String, moleMass: Double) {
        compoundList.add(Compound(name,symbol,moleMass))
        saveCompoundList(context)
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
