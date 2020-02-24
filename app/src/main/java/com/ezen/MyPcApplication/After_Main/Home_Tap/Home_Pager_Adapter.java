package com.ezen.MyPcApplication.After_Main.Home_Tap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.ezen.MyPcApplication.R;

public class Home_Pager_Adapter extends PagerAdapter {

    setClickListener listener;


    // R.drawable.(사진파일이름)으로 images 배열 생성
    private int[] images = {R.drawable.fifaevent, R.drawable.cartrider, R.drawable.maple};
    private String[] url = {"https://events.fifaonline4.nexon.com/200301/burning?utm_source=pc&utm_medium=boardThumb&utm_campaign=200301_burning", "http://kart.nexon.com/Events/2020/0102/PCevent.aspx", "https://maplestory.nexon.game.naver.com/News/Event/Ongoing/234"};
    private LayoutInflater inflater;
    private Context context;

    // 해당 context가 자신의 context 객체와 똑같이 되도록 생성자를 만듬
    public Home_Pager_Adapter(Context context, setClickListener listener){
        this.context = context;
        this.listener = listener;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // object를 LinearLayout 형태로 형변환했을 때 view와 같은지 여부를 반환
//        return view == ((LinearLayout)object); 으로 했을때 오류 나서 View 로 바꿈..
        return view == ((View)object);
    }

    // 각각의 item을 인스턴스 화
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //초기화
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.pc_event, container, false);
        ImageView imageView = v.findViewById(R.id.imageViewEvent);
        imageView.setImageResource(images[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 해당 이벤트사이트로
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url[position]));
                listener.setClick(intent);
            }
        });
        container.addView(v);
        return v;
    }

    //할당을 해제
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
//        super.destroyItem(container, position, object);
    }

    interface setClickListener{
        void setClick(Intent intent);
    }

}
