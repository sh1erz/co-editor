package com.example.coedit.ui.widgets.hscrollmenu

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.coedit.databinding.ItemMenuBinding

class MenuItemAdapter(
    private val menuItems: List<EditMenuItem>,
    private val onItemClickListener: OnItemClickListener,
    private val iconWidth: Int,
    private val iconHeight: Int,
    private val colorActive: Int,
    private val colorInactive: Int,
    private val itemMarginTop: Int = 0,
    private val itemMarginBottom: Int = 0,
    private val itemMarginLeft: Int = 0,
    private val itemMarginRight: Int = 0,
    private val showTitle: Boolean = false,
    private val itemTextSize: Int = 12
) : RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder>() {

    var selected = -1
    private var activeView: Pair<ImageView, Int>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        return MenuItemViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val item = menuItems[position]
        with(holder.binding) {
            menuView.setOnClickListener {
                onItemClickListener.onItemClick(item)

                //already chosen and want to unselect
                if(position == selected){
                    setIcon(menuIcon, item.icon, selected = false)
                    selected = -1
                    activeView = null
                    return@setOnClickListener
                }
                setIcon(menuIcon, item.icon, selected = true)
                activeView?.let { setIcon(it.first, it.second, selected = false) }
                activeView = menuIcon to item.icon
                selected = position
            }
            menuView.setPadding(itemMarginLeft, itemMarginTop, itemMarginRight, itemMarginBottom)
            setIconAttributes(menuIcon, item, position == selected)
            if (showTitle) setTitleAttributes(menuTitle, item, position == selected)
        }
    }

    override fun getItemCount(): Int = menuItems.size

    private fun setIconAttributes(icon: ImageView, item: EditMenuItem, selected: Boolean) {
        icon.apply {
            setIcon(icon, item.icon, selected)

            val params = layoutParams
            params.apply {
                width = iconWidth
                height = iconHeight
            }
            layoutParams = params
        }
    }

    private fun setTitleAttributes(title: TextView, item: EditMenuItem, selected: Boolean) {
        title.apply {
            text = context.getString(item.titleId)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, itemTextSize.toFloat())
            setTextColor(if (selected) colorActive else colorInactive)
            visibility = View.VISIBLE
        }
    }

    private fun setIcon(icon: ImageView, iconId: Int, selected: Boolean) {
        val drawable = AppCompatResources.getDrawable(icon.context, iconId)
            ?: throw Exception("no drawable found with id $iconId")
        val iconColor = if (selected) colorActive else colorInactive
        DrawableCompat.setTint(
            DrawableCompat.wrap(drawable),
            iconColor
        )
        icon.setImageDrawable(drawable)
    }

    class MenuItemViewHolder(val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(item: EditMenuItem)
    }

}