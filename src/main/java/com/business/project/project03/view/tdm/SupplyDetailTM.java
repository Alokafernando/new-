package com.business.project.project03.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SupplyDetailTM implements Comparable<SupplyDetailTM> {

        private String partId;
        private String partName;
        private int cartQuantity;
        private double unitPrice;
        private double totalPrice;
        private Button removeButton;



        @Override
        public int compareTo(SupplyDetailTM o) {
                return partId.compareTo(o.partId);
        }
}
