package com.example.programmingarea.dataclass;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Questions {
    public String title;
    public List<String> questions;
    public String code = "";
    public int rightId;
}
