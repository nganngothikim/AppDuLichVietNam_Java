package com.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Detail extends AppCompatActivity {
    ListView lstvDanhGia;
    ImageView imgvDD;
    TextView tvTenDD, tvTinhThanh, tvMoTa, tvDiaChi;
    EditText edtBinhLuan;
    Button btnGui;
    ImageButton btnBack, btnlike;
    Location location = new Location();
    ArrayList<Comment> commentArrayList = new ArrayList<>();
    CommentAdapter commentAdapter;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        addControls();
        Intent intent = getIntent();
        String maDD = intent.getStringExtra("MaDD");

        dbHandler = new DBHandler(this);
        location = dbHandler.getLocation(maDD);
        //kiểm tra địa điểm đã có trong danh sách yêu thích của user hiện tại chưa-> có thì cho tim màu đỏ
        if(dbHandler.checkFavList(User.userName, location.getMaDD())== false)
        {
            btnlike.setColorFilter(Color.RED);
        }
        else
        {
            btnlike.setColorFilter(null);
        }

        //Đổ dữ liệu
        imgvDD.setImageBitmap(Location.convertStringToBitmapFromAccess(this, location.getHinhAnh()));
        tvTenDD.setText(location.getTenDD());
        tvTinhThanh.setText(location.getTenTinh());
        tvMoTa.setText(location.getMoTa());
        tvDiaChi.setText(location.getDiaChi());

        //Quay về trang trước
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail.this, LocationActivity.class);
                intent.putExtra("MaTinh", location.getMaTinh());
                startActivity(intent);
            }
        });

        //Xử lý yêu thích
        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(User.userName.isEmpty())
                {
                    Toast.makeText(Detail.this, "Bạn cần phải đăng nhập", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (btnlike.getColorFilter() == null) {
                    if (dbHandler.addFavList(User.userName, location.getMaDD()) == 1) {
                        Toast.makeText(Detail.this, "Đã thêm địa điểm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Detail.this, "Địa điểm đã có trong danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    }
                    // ImageButton hiện đang có màu mặc định là màu trắng
                    btnlike.setColorFilter(Color.RED); // đổi màu thành đỏ
                } else {
                    if (dbHandler.deleteFavList(User.userName, location.getMaDD()) == 1) {
                        Toast.makeText(Detail.this, "Xóa địa điểm khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Detail.this, "Xóa địa điểm thất bại !", Toast.LENGTH_SHORT).show();
                    }
                    // ImageButton hiện đang có màu filter khác màu trắng
                    btnlike.setColorFilter(null); // đổi màu về mặc định
                }
            }
        });


        //Load dữ liệu bình luận
        commentArrayList = dbHandler.getListComment(maDD);
        commentAdapter = new CommentAdapter(this, R.layout.lv_layout, commentArrayList);
        lstvDanhGia.setAdapter(commentAdapter);

        //Xử lý thêm bình luận
        String str_binhLuan = edtBinhLuan.getText().toString();
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(User.userName.isEmpty())
                {
                    Toast.makeText(Detail.this, "Bạn cần phải đăng nhập", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edtBinhLuan.getText().toString().isEmpty())
                    Toast.makeText(Detail.this, "Bạn chưa nhập bình luận", Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(Detail.this, edtBinhLuan.getText().toString(), Toast.LENGTH_SHORT).show();
                    Calendar calendar = Calendar.getInstance();
                    Date date = calendar.getTime();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String tg = format.format(date);
                    if (dbHandler.addCommentList(User.userName, location.getMaDD(), tg, edtBinhLuan.getText().toString()) == 1) {
                        Toast.makeText(Detail.this, "Bình luận thành công", Toast.LENGTH_SHORT).show();

                        commentArrayList = dbHandler.getListComment(maDD);
                        commentAdapter = new CommentAdapter(Detail.this, R.layout.lv_layout, commentArrayList);
                        lstvDanhGia.setAdapter(commentAdapter);

                        edtBinhLuan.setText("");
                    } else {
                        Toast.makeText(Detail.this, "Bình luận thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        lstvDanhGia.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Comment c = commentArrayList.get(i);
                confirmDelete(c.getId());
                return false;
            }
        });
    }
    private void confirmDelete(int position) {//sử dụng alertdialog xác nhận xóa bình luận
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Bạn có chắc muốn xóa bình luận không?");
        alertDialog.setIcon(R.mipmap.icon);
        alertDialog.setTitle("Thông báo!");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int kt= dbHandler.deleteCMT(User.userName,position);
                if(kt== 1)
                {
                    Toast.makeText(Detail.this, "Xoá bình luận thành công", Toast.LENGTH_SHORT).show();
                    commentArrayList = dbHandler.getListComment(location.maDD);
                    commentAdapter = new CommentAdapter(Detail.this, R.layout.lv_layout, commentArrayList);
                    lstvDanhGia.setAdapter(commentAdapter);
                }
                else {
                    if (User.userName == "")
                        Toast.makeText(Detail.this, "Đăng nhập để xoá bình luận", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Detail.this, "Không thể xoá bình luận của người khác", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }
    private void addControls() {
        btnBack = (ImageButton) findViewById(R.id.btnBackLocation);
        imgvDD = (ImageView) findViewById(R.id.imgvDD);
        tvTenDD = (TextView) findViewById(R.id.tvTenDD);
        tvTinhThanh = (TextView) findViewById(R.id.tvTinhThanh);
        tvMoTa = (TextView) findViewById(R.id.tvMoTa);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        lstvDanhGia = (ListView) findViewById(R.id.lstvDanhGia);
        btnGui = (Button) findViewById(R.id.btnGui);
        edtBinhLuan = (EditText) findViewById(R.id.edtBinhLuan);
        btnlike = (ImageButton) findViewById(R.id.btnlike);
    }
}