package com.example.coedit.ui.widgets.hscrollmenu

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.graphics.ColorUtils
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coedit.R
import com.example.coedit.databinding.HscrollMenuBinding

class HorizontalScrollMenuView(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs), MenuItemAdapter.OnItemClickListener {

    private var _context: Context? = null
    private var itemAdapter: MenuItemAdapter? = null
    private val menuItems = EditMenuItem.values().toList()

    private var itemSeletected = 0

    //attrs
    private var iconWidth = 24
    private var iconHeight = 24
    private var backgroundMenuColor =
        getColor(context, com.google.android.material.R.attr.colorPrimary)
    //todo color accent in dark mode
    private val colorActive: Int =
        getColor(context, com.google.android.material.R.attr.colorOnPrimary)
    private val colorInactive: Int = ColorUtils.setAlphaComponent(
        getColor(context, com.google.android.material.R.attr.colorOnPrimary),
        ALPHA_INACTIVE.toInt()
    )
    private var item_marginTop = 0
    private var item_marginBottom = 0
    private var item_marginLeft = 0
    private var item_marginRight = 0
    private var item_colorSelected = Color.parseColor("#0099cc")
    private var item_textSize = 12
    private var _binding: HscrollMenuBinding? = null
    private val binding get() = _binding!!

    init {
        _context = context
        if (attrs != null) initAttributes(attrs)
        val inflater = LayoutInflater.from(context)
        _binding = HscrollMenuBinding.inflate(inflater, this, true)

        with(binding) {
            hscrollView.setBackgroundColor(backgroundMenuColor)
            recyclerViewItems.layoutManager = LinearLayoutManager(
                _context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            itemAdapter = MenuItemAdapter(
                menuItems = menuItems,
                onItemClickListener = this@HorizontalScrollMenuView,
                iconWidth = iconWidth,
                iconHeight = iconHeight,
                colorActive = colorActive,
                colorInactive = colorInactive
            )
            recyclerViewItems.adapter = itemAdapter
        }

    }

    private fun initAttributes(attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.HorizontalScrollMenuView, 0, 0)
        iconHeight = a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_icon_height, 60)
        iconWidth = a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_icon_width, 60)
        backgroundMenuColor = a.getColor(
            R.styleable.HorizontalScrollMenuView_backgroundColor,
            backgroundMenuColor
        )

        item_colorSelected =
            a.getColor(R.styleable.HorizontalScrollMenuView_item_colorSelected, item_colorSelected)
        itemSeletected =
            a.getInt(R.styleable.HorizontalScrollMenuView_item_selected, itemSeletected)
        item_marginTop =
            a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_item_marginTop, 0)
        item_marginBottom =
            a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_item_marginBottom, 0)
        item_marginLeft =
            a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_item_marginLeft, 0)
        item_marginRight =
            a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_item_marginRight, 0)
        item_textSize =
            a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_item_textSize, 12)

        a.recycle()

    }


    override fun onItemClick(item: EditMenuItem) {

    }

    override fun onSaveInstanceState(): Parcelable {
        super.onSaveInstanceState()
        return bundleOf(ITEM_SELECTED to itemSeletected)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val bundle = state as? Bundle
        bundle?.let {
            itemSeletected = it.getInt(ITEM_SELECTED, 0)
        }
        super.onRestoreInstanceState(state)
    }

    companion object {
        const val ALPHA_INACTIVE = 255 * 0.6
        const val ITEM_SELECTED = "item_selected"

        private fun getColor(context: Context, resId: Int): Int {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(
                resId,
                typedValue,
                true
            )
            return typedValue.data
        }
    }

}