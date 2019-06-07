package kz.pandamobile.pandalib.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration : RecyclerView.ItemDecoration {

    private var mItemOffset: Int = 0

    constructor(itemOffset: Int) {
        mItemOffset = itemOffset
    }

    constructor(itemOffset: Float) {
        mItemOffset = itemOffset.toInt()
    }

    constructor(context: Context, @DimenRes itemOffsetId: Int) : this(
        context.resources.getDimensionPixelSize(
            itemOffsetId
        )
    )

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }
}