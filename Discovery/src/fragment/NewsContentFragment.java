package fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.lcu.lfz.Discovery.R;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/10.
 */
public class NewsContentFragment extends Fragment implements View.OnClickListener {

    private TextView tv_title,tv_content;
    private EditText et_comment;
    private Button btn_submit;
    private ListView lv_comment;
    private ScrollView scrollView;

    private AVObject news;
    private SimpleAdapter adapter;
    private String title,content;
    private ArrayList commentList;
    private Map<String,String> commentMap;
    private String[] strings = {"author","content"};
    private int[] ints = { R.id.item_comment_author,R.id.item_comment_content};


    public NewsContentFragment(AVObject news){
        this.news = news;
        title = news.getString("title");
        content = news.getString("content");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_newscontent,container,false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        tv_title = (TextView) v.findViewById(R.id.news_title);
        tv_content = (TextView) v.findViewById(R.id.news_content);
        lv_comment = (ListView) v.findViewById(R.id.news_comment);
        et_comment = (EditText) v.findViewById(R.id.et_comment);
        btn_submit = (Button) v.findViewById(R.id.btn_submit);
        scrollView = (ScrollView) v.findViewById(R.id.news_scrollView);
        lv_comment.setEmptyView(EmptyComment());

        btn_submit.setOnClickListener(this);
        tv_title.setText(title);
        tv_content.setText(content);
        getData();
        scrollView.smoothScrollTo(0,0);
    }

    private void getData() {
//        AVQuery avQuery = new AVQuery("Comment");
//        ArrayList<Map<String,Object>> commentList = new ArrayList<>();
//        avQuery.findInBackground(new FindCallback() {
//            @Override
//            public void done(List list, AVException e) {
//                for(Object object : list){
//                    AVObject avObject = (AVObject) object;
//                    Map map = new HashMap();
//                    map.put("author",avObject.getString("author"));
//                    map.put("content",avObject.getString("content"));
//                    commentList.add(map);
//                }
//                adapter = new SimpleAdapter(getActivity(),commentList,R.layout.item_comment,strings,ints);
//                lv_comment.setAdapter(adapter);
//            }
//        });

        commentList = new ArrayList<>();
        commentMap = news.getMap("comment");
        if(commentMap != null)
            for(String key : commentMap.keySet()){
                Map resultMap = new HashMap<>();
                resultMap.put("author",key);
                resultMap.put("content",commentMap.get(key));
                commentList.add(resultMap);
            }
        adapter = new SimpleAdapter(getActivity(),commentList,R.layout.item_comment,strings,ints);
        lv_comment.setAdapter(adapter);
    }

    private View EmptyComment() {
        TextView tv = new TextView(getActivity());
        tv.setText("木有评论，快抢沙发~");
        return tv;
    }

    @Override
    public void onClick(View v) {

        if(commentMap == null){
            commentMap = new HashMap<String, String>();
        }
        String comment_content = et_comment.getText().toString();
        String comment_author = AVUser.getCurrentUser().getUsername();
        HashMap hashMap = new HashMap();
        hashMap.put(comment_author,comment_content);
        commentMap.put(comment_author,comment_content);
        commentList.add(hashMap);
        news.put("comment",commentMap);
        et_comment.setText("");
        news.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e == null) {
                    Toast.makeText(getActivity(), "回复成功", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getActivity(), "回复失败:"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
