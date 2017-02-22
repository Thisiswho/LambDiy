package fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.lcu.lfz.Discovery.LoginActivity;
import cn.lcu.lfz.Discovery.R;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetDataCallback;
import tools.MyTools;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2017/1/25.
 * 个人信息界面
 */
public class MeFragment extends Fragment implements View.OnClickListener, LoadKeyBoardDialogFragment.LoadKeyBoardEventListener {

    public static final int SELECT_PHOTO = 1;

    private ImageView iv_head;
    private TextView tv_name;
    private View v;
    private Button btn_logoff,btn_myPro;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Drawable drawable = (Drawable) msg.obj;
            iv_head.setImageDrawable(drawable);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_me,container,false);
        iv_head = (ImageView) v.findViewById(R.id.fragment_me_head);
        tv_name = (TextView) v.findViewById(R.id.fragment_me_name);
        btn_logoff = (Button) v.findViewById(R.id.fragment_me_logoff);
//        btn_changePwd = (Button) v.findViewById(R.id.btn_change_password);
        btn_myPro = (Button) v.findViewById(R.id.btn_myPro);
        btn_logoff.setOnClickListener(this);
        iv_head.setOnClickListener(this);
//        btn_changePwd.setOnClickListener(this);
        btn_myPro.setOnClickListener(this);
        initData();
        return v;
    }

    private void initData() {
        AVUser user = AVUser.getCurrentUser();
        String name = user.getUsername();
        tv_name.setText(name);
        AVFile headFile = user.getAVFile("userhead");
        if(headFile == null)
            return;
        headFile.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, AVException e) {
                Drawable bitmap = new MyTools().bytes2Drawable(bytes);
                Message msg = new Message();
                msg.obj = bitmap;
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fragment_me_head:
                ModifyHead();
                break;
            case R.id.fragment_me_logoff:
                AVUser.logOut();
                Toast.makeText(getActivity(), "注销成功", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
                break;
//            case R.id.btn_change_password:
//                LeancloudTools.changePassword();
//                break;
            case R.id.btn_myPro:
                LoadKeyBoardDialogFragment loadKeyBoardDialogFragment = new LoadKeyBoardDialogFragment(this,true);
                loadKeyBoardDialogFragment.show(getFragmentManager(),"load_me_keyboard");
                break;
        }
    }



    private void ModifyHead(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        v = inflater.inflate(R.layout.dialog_modify_head,null);
        ImageView iv_head = (ImageView) v.findViewById(R.id.img_newHead);
        Button btn_modify = (Button) v.findViewById(R.id.btn_modify);
        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,SELECT_PHOTO);
            }
        });
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVUser user = AVUser.getCurrentUser();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,0,baos);
                byte[] bitmapByte = baos.toByteArray();
                AVFile file = new AVFile("userhead",bitmapByte);
                user.put("userhead",file);
                Message msg = new Message();
                msg.obj = MyTools.bitmap2Drawable(bitmap);
                handler.sendMessage(msg);
                Toast.makeText(getActivity(), "更新成功！", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setView(v);
        builder.create().show();
    }

    Bitmap bitmap = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            ContentResolver cr = getActivity().getContentResolver();
            try {
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                ImageView imageView = (ImageView) v.findViewById(R.id.img_newHead);
                imageView.setImageDrawable(MyTools.bitmap2Drawable(bitmap));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onLoad(String destId) {

    }
}
