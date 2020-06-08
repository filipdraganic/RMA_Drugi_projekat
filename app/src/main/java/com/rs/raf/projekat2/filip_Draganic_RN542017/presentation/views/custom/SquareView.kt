package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.rs.raf.projekat2.filip_Draganic_RN542017.extensions.toPx
import com.rsrafprojekat1.Filip_Draganic_RN542017.R

import timber.log.Timber
import java.util.Collections.max

class SquareView : View {
    constructor(context: Context)
            : super(context)
    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    private var rect: Rect = Rect()
    private var paint: Paint = Paint()
    private val redStrokePaint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.black)
        it.style = Paint.Style.STROKE
        it.strokeWidth = 4.toPx().toFloat()
    }
    private val blueFillPaint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.blue)
        it.style = Paint.Style.FILL
    }
    private val whiteFillPaint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.white)
        it.style = Paint.Style.FILL
    }

    var heightDivider = 1

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Timber.e("On measure")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Timber.e("On layout")
    }

    private var lista :MutableList<Int> = mutableListOf()

    fun setLista(list: MutableList<Int>){
        lista = list
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Timber.e("On draw")

        // Set dimensions of the first shape to be drawn
        val left = 0
        val top = 0
        val bottom = height / heightDivider
        val right = width
        rect.set(left, top, right, bottom)
        //Draw it with blue paint (fill)
        canvas?.drawRect(rect, whiteFillPaint)
        //Draw it with red paint (only stroke)
        canvas?.drawRect(rect, redStrokePaint)

        // Set dimensions of the second shape to be drawn
//        val left2 = right
//        val top2 = top
//        val bottom2 = bottom
//        val right2 = width
//        rect.set(left2, top2, right2, bottom2)
//        //Draw it with blue paint (fill)
//        canvas?.drawRect(rect, blueFillPaint)
//        //Draw it with red paint (only stroke)
//        canvas?.drawRect(rect, redStrokePaint)

        val sirina = width/5
        val visina = height



        val max = max(lista)

        for (i in 5 downTo 0 ){
            val levo = sirina*i + 10
            val vrh = height/(lista[i]+1)
            val dole = visina
            val desno = sirina*(i+1) - 10
            rect.set(levo, vrh, desno, dole)
            canvas?.drawRect(rect, blueFillPaint)
            canvas?.drawRect(rect, redStrokePaint)
        }

    }
}
