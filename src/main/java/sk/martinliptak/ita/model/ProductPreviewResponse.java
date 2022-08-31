package sk.martinliptak.ita.model;

import lombok.Data;

@Data
public class ProductPreviewResponse {
    private String filename;
    private byte[] bytes;
}
