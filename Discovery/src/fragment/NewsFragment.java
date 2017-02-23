package fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.lcu.lfz.Discovery.MainActivity;
import cn.lcu.lfz.Discovery.R;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import view.RefreshableView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/1/25.
 */
public class NewsFragment extends Fragment{



    private LinearLayout linearLayout_title;
    private ListView news_listView;
    private RefreshableView refreshable_view;
    private SimpleAdapter mNewsListAdapter;
    private ArrayList<Map<String,Object>> myNewsData;
    private String[] news_Strings = new String[]{ "title","icon","content","time" };
    private int[] news_Ints = new int[]{ R.id.item_news_title,R.id.item_news_icon,R.id.item_news_content,R.id.item_news_time };
    private List news_list;
    private int mCategory = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news,container,false);
        initView(v);
        initData();
        return v;
    }

    private void initView(View v) {
        linearLayout_title = (LinearLayout) v.findViewById(R.id.category_layout);
        news_listView = (ListView) v.findViewById(R.id.news_list);
        refreshable_view = (RefreshableView) v.findViewById(R.id.refreshable_view);

    }
    private void initData() {
        String[] categories = getResources().getStringArray(R.array.categories);
        for(int i = 0; i < categories.length; i++){
            String category = categories[i];
            TextView tv = new TextView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(10,0,10,0);
            tv.setLayoutParams(params);
            tv.setText(category);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(100,0,100,0);
            tv.setBackground(getResources().getDrawable(R.drawable.category_shape));
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCategory = finalI;
                    getNews();
                }
            });
            linearLayout_title.addView(tv);
        }
        getNews();
        refreshable_view.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(1000);
                    getNews();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                refreshable_view.finishRefreshing();
            }
        },0);
        news_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                myNewsData.get(position);
                NewsContentFragment newsContentFragment = new NewsContentFragment((AVObject) news_list.get(position));
                ((MainActivity)getActivity()).loadFragment(R.id.main_container,newsContentFragment);
            }
        });
    }

    private int getNews(){
        myNewsData = new ArrayList<>();
        AVQuery newsQuery = new AVQuery("News");
        if(mCategory != 0)
            newsQuery.whereEqualTo("category",mCategory);
        newsQuery.findInBackground(new FindCallback() {
            @Override
            public void done(List list, AVException e) {
                updateDatas(list);
            }
        });
        return 0;
    }

    private void updateDatas(List list){
        news_list =  list;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Object obj : list) {
            AVObject category = (AVObject) obj;
            HashMap<String, Object> map = new HashMap<>();
            map.put("title", category.getString("title"));
            map.put("content", category.getString("content"));
            map.put("time", simpleDateFormat.format(category.getCreatedAt()));
            myNewsData.add(map);
        }
        mNewsListAdapter = new SimpleAdapter(getActivity(),myNewsData,R.layout.item_news,news_Strings,news_Ints);
        news_listView.setAdapter(mNewsListAdapter);
    }
}
