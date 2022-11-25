package com.example.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.food.retrofit.RetrofitFoodClient;
import com.example.food.retrofit.RetrofitFoodData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private RetrofitFoodData retrofitFoodData;
    private RetrofitFoodClient retrofitFoodClient;

    private TextView tv_food;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        tv_food = findViewById(R.id.tv_food);

        readExcel();
    }

    private void readExcel() {
        try {
            InputStream inputStream = getBaseContext().getResources().getAssets().open("/excel/cinema_name.csv");

            Workbook workbook = new HSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();

            for (int i = 0; i < rows; i++) {
                ArrayList<String> arrayList = new ArrayList<>();

                Row row = sheet.getRow(i);

                if (row == null) continue;

                int cells = row.getPhysicalNumberOfCells();

                for (int k = 0; k <= cells; i++) {
                    Cell cell = sheet.getRow(i).getCell(k);

                    if (cell == null) continue;

                    String value = "";

                    switch (cell.getCellType()) {
                        case FORMULA:
                            value = cell.getCellFormula();
                            break;

                        case BLANK:
                            value = cell.getBooleanCellValue() + "";
                            break;

                        case STRING:
                            value = cell.getStringCellValue() + "";
                            break;

                        case NUMERIC:
                            value = cell.getNumericCellValue() + "";
                            break;

                        case ERROR:
                            value = cell.getErrorCellValue() + "";
                            break;

                        default:
                            break;
                    }

                    arrayList.add(value);
                }

                tv_food.setText((CharSequence) arrayList);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}