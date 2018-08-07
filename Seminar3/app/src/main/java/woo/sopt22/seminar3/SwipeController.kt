package woo.sopt22.seminar3

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE
import android.view.MotionEvent

@SuppressLint("ClickableViewAccessibility")

class SwipeController : ItemTouchHelper.Callback() {
    private var swipeBack = false // 밀었다가 되돌아갈때는 조정할지 말지
    private var buttonShowedState = ButtonsState.GONE // 만들 버튼이 두가지인데, 이 버튼들의 상태가 왼쪽은 보이냐 오른쪽은 보이냐의 상태를 조정할 것
    private var buttonInstance : RectF? = null // 화면에 그릴 때, xml이 아니라 코드를 통해서 할 때 사용
    // 버튼을 네모칸으로 만들 것인데 구역 설정을 할 때 사용함
    private var currentItemViewHolder : RecyclerView.ViewHolder? = null

    private val buttonWidth = 300f // f는 float형으로 만들기 위해서 붙임

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if(swipeBack){ // 뒤로 돌아가는 것이 활성화되어 있다면
            swipeBack = (buttonShowedState != ButtonsState.GONE) // 값이 다른 것이 true면 true로
            return 0
            // 값이 다르지 않고 같다면 false로 swipeBack에 들어가게 된다.

            /*
            if(buttonShowedState !=ButtonsState.GONE)
            swipeBack = true
            else
            swipeBack = false
            와 같은 문장이다.
            *
            * */
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)

    }

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        return ItemTouchHelper.Callback.makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false

    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {

    }

    private fun setItemsClickable(recyclerView: RecyclerView?, isClickable : Boolean){

        // 인자로 들어갈 리사이클러뷰의 아이템에 clickable을 각각에게 넘겨주겠다는 의미
        for(i in 0 until recyclerView!!.childCount)
            recyclerView!!.getChildAt(i).isClickable = isClickable
    }


    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

        var dX = dX
        // 오버라이드한 함수의 dX는 상수 값이기 때문에 가지고 와서 변경하기 위해서 위처럼 작성함

        // 스와이프 되서 버튼이 보이긴 하는데 왼쪽에서 보이는지 오른쪽에서 보이는지 처리를 해야함
        if(actionState == ACTION_STATE_SWIPE){
            if(buttonShowedState !=ButtonsState.GONE){
                if(buttonShowedState == ButtonsState.LEFT_VISIBLE)
                    dX = Math.max(dX, buttonWidth)
                if(buttonShowedState == ButtonsState.RIGHT_VISIBLE)
                    dX = Math.min(dX, -buttonWidth)

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                // 버튼 상태에 따라서 지정된 값을 가질 수 있다.
            } else{ // GONE 상태가 아니라 진짜 보이는 상태였다면
                setTouchListener(c!!, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

            }
        }

        if(buttonShowedState == ButtonsState.GONE){
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

        currentItemViewHolder = viewHolder
    }

    // 눌렀다가 떼었을 때, dx : x값의 위치, dy : y값의 위치
    private fun setTouchUpListener(c : Canvas, recyclerView: RecyclerView?
                                   , viewHolder: RecyclerView.ViewHolder?, dX : Float, dY : Float
                                   , actionState : Int, isCurrentlyActive : Boolean){

        // 인자로 넘길 리사이클러뷰에 터치 리스너를 달아 줄 것

        recyclerView!!.setOnTouchListener { v, event ->

            if(event.action == MotionEvent.ACTION_UP){ // Up인 상태에서 들어오면

                super@SwipeController.onChildDraw(c, recyclerView, viewHolder, 0f, dY, actionState, isCurrentlyActive)
                recyclerView.setOnTouchListener { v, event ->
                    return@setOnTouchListener false // Up일 때의 작업을 없애기 위함
                }

                setItemsClickable(recyclerView, true) // 두번 터치한게 아닌 이상은 떼어냈을 때, 리사이클러뷰의 아이템을 잡았다가 떼어냈을 때는 다른 아이템을 가질 수 있도록 함
                swipeBack = false


                //if(()) -> 버튼 이벤트로 무언가를 처리 할 예정.

                buttonShowedState = ButtonsState.GONE
                currentItemViewHolder = null

            }

            return@setOnTouchListener false
        }

    }

    // Clickable이 활성화 된것이 무엇이냐면
    // 하나의 아이템을 눌렀을 때 다른 아이템을 누를 수 없도록 clickable을 구현함
    // 하지만, 눌렀다가 떼었을 때는 다른 아이템을 클릭할 수 있다. 이게 ACTION_UP을 구현한 것!


    private fun setTouchDownListener(c : Canvas, recyclerView: RecyclerView?
                                     , viewHolder: RecyclerView.ViewHolder?, dX : Float, dY : Float
                                     , actionState : Int, isCurrentlyActive : Boolean){
        recyclerView!!.setOnTouchListener { v, event ->

            if(event.action == MotionEvent.ACTION_DOWN){ // DOWN 눌렀을 때 UP을 하거나 다른 작업을 할 수 있도록 설정하는 부분이라고 생각할 수 있다.
                setTouchUpListener(c, recyclerView,viewHolder,dX,dY, actionState, isCurrentlyActive)
            }


            return@setOnTouchListener false
        }
    }


    private fun setTouchListener(c : Canvas, recyclerView: RecyclerView?
                                 , viewHolder: RecyclerView.ViewHolder?, dX : Float, dY : Float
                                 , actionState : Int, isCurrentlyActive : Boolean){


        recyclerView!!.setOnTouchListener { v, event ->


            swipeBack = (event.action == MotionEvent.ACTION_CANCEL ||
                    event.action == MotionEvent.ACTION_UP)

            if(swipeBack){
                if(dX < -buttonWidth)
                    buttonShowedState = ButtonsState.RIGHT_VISIBLE
                else if(dX > buttonWidth)
                    buttonShowedState = ButtonsState.LEFT_VISIBLE


                if(buttonShowedState != ButtonsState.GONE){
                    setTouchDownListener(c, recyclerView,viewHolder,dX,dY, actionState
                            , isCurrentlyActive)
                    setItemsClickable(recyclerView, false)

                }
            }

            return@setOnTouchListener false // false가 아니면 중복 터치가 될 수도 있음
        }
    }


    private fun drawButtons(c : Canvas, viewHolder : RecyclerView.ViewHolder){




        val buttonWidthWithoutPadding = buttonWidth - 20
        val corners = 16f // 코너 처리
        val itemView = viewHolder.itemView
        val p = Paint()

        val leftButton = RectF(itemView.left.toFloat()
                , itemView.top.toFloat()
                , itemView.left + buttonWidthWithoutPadding // 오른쪽좌표는 들어오는 값에 따라
                , itemView.bottom.toFloat()) // 소수형으로 움직임, 왼쪽 버튼



        p.color = Color.BLUE
        c.drawRoundRect(leftButton, corners, corners, p)
        drawText("EDIT", c, leftButton, p)


        val rightButton = RectF(itemView.right - buttonWidthWithoutPadding
                , itemView.top.toFloat()
                , itemView.right.toFloat()
                , itemView.bottom.toFloat()) // 소수형으로 움직임, 왼쪽 버튼


        p.color = Color.RED
        c.drawRoundRect(rightButton, corners, corners, p)
        drawText("DELETE",c , rightButton, p)



        buttonInstance = null
        if(buttonShowedState == ButtonsState.LEFT_VISIBLE)
            buttonInstance = leftButton
        else if (buttonShowedState == ButtonsState.RIGHT_VISIBLE)
            buttonInstance = rightButton
    }


    private fun drawText(text : String, c : Canvas, button : RectF, p : Paint){

        val textSize = 60f
        p.color = Color.WHITE
        p.isAntiAlias = true
        p.textSize = textSize


        val textWidth = p.measureText(text) // text 길이에 따라서 width를 반환해준다.
        c.drawText(text, button.centerX() - textWidth/2, button.centerY() + textSize/2, p)
        // 버튼의 중앙에 text를 배치하기 위해서

    }

    fun onDraw(c : Canvas){
        if(currentItemViewHolder !=null)
            drawButtons(c, currentItemViewHolder!!)

    }

}