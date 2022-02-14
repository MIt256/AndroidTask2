package com.example.tasktwo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktwo.databinding.ElementItemBinding
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.FragmentNavigatorExtras

class ElementAdapter : RecyclerView.Adapter<ElementAdapter.ElementHolder>() {

    var elementList = ArrayList<Element>()

    class ElementHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ElementItemBinding.bind(view)

        fun bind(element: Element) = with(binding) {
            elementImage.setImageResource(element.imageId)
            titleText.text = element.title
            descriptionText.text = element.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_item, parent, false)
        return ElementHolder(view)
    }

    override fun onBindViewHolder(holder: ElementHolder, position: Int) {
        with(holder.binding) {
            ViewCompat.setTransitionName(elementImage, elementList[position].imageId.toString())
            ViewCompat.setTransitionName(titleText, elementList[position].title)
            ViewCompat.setTransitionName(descriptionText, elementList[position].description)
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            val bundle = Bundle()
            bundle.putInt("imageId", elementList[position].imageId)
            bundle.putString("title", elementList[position].title)
            bundle.putString("description", elementList[position].description)

            val extras = FragmentNavigatorExtras(
                holder.binding.elementImage to elementList[position].imageId.toString(),
                holder.binding.titleText to elementList[position].title,
                holder.binding.descriptionText to elementList[position].description
            )

            Navigation
                .findNavController(holder.binding.root)
                .navigate(
                    R.id.action_firstFragment_to_secondFragment,
                    bundle,
                    null,
                    extras
                )
        })

        holder.bind(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun addElements(count: Int) {
        for (i in 1..count) {
            var element = Element(R.drawable.image1, "Title $i", "Description $i")
            elementList.add(element)
        }
        notifyDataSetChanged()
    }

}