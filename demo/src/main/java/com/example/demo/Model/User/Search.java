package com.example.demo.Model.User;

import java.sql.SQLException;
import java.util.List;

public interface Search {
    public List<String> search(String placeName) throws SQLException;
}
