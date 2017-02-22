package fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import cn.lcu.lfz.Discovery.KeyBoardActivity;
import cn.lcu.lfz.Discovery.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/25.
 * 设计功能的选择页面，采用GridView视图给功能分类。
 */
public class DiyFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final int DIY_KEYBOARD = 0;

    private GridView gridView;
    private List<Map<String,Object>> data_list;
    private SimpleAdapter simpleAdapter;

    private int[] icon = {R.drawable.icon_kb};

    private String[] iconName = {"键盘"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_diy,container,false);
        gridView = (GridView) v.findViewById(R.id.fragment_diy_gridView);
        initData();
        String[] from = {"image","text"};
        int[] to = {R.id.item_icon,R.id.item_iconName};
        simpleAdapter = new SimpleAdapter(getActivity(),data_list,R.layout.item_gridview,from,to);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
        return v;
    }

    private void initData() {
        data_list = new ArrayList<Map<String, Object>>();
        for(int i = 0;i<icon.length;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case DIY_KEYBOARD:
//                废弃代码：原因是无法对单独的Fragment进行横屏设置
//                KeyBoardFragment keyBoardFragment = new KeyBoardFragment();
//                loadFragment(R.id.main_container,keyBoardFragment);

                Intent keyboardIntent = new Intent(getActivity(), KeyBoardActivity.class);
                startActivity(keyboardIntent);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

//    private void loadFragment(int containerId,Fragment fragment){
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().addToBackStack("").add(containerId,fragment).commit();
//    }
}
