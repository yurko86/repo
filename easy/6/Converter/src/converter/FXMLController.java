package converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author iurii
 */
public class FXMLController implements Initializable {

    public enum DataTransfer {
        Tbs("Terabyte per second", 8000000000000L),
        Tibits("Tibibits per second", 1100000000000L),
        TBps("Terabit per second", 1000000000000L),
        Gbs("Gigabyte per second", 8000000000L),
        Gibits("Gibibits per second", 1074000000),
        GBps("gigabit per second", 1000000000),
        Mbs("Megabyte per second", 8000000),
        Mibits("mebibits per second", 1049000),
        MBps("megabit per second", 1000000),
        Kbs("Megabyte per second", 8000),
        Kibits("Kibibits per second", 1024),
        Kbps("kilobit per second", 1000),
        Bps("bit per second", 1);

        private final String name;
        private final long ratio;

        DataTransfer(String name, long ratio) {
            this.name = name;
            this.ratio = ratio;
        }

        public long getRatio() {
            return this.ratio;
        }

    }

    public enum DataStorage {
        Tebibit("Tebibit", 1100000000000L),
        Terabit("Tbit", 1000000000000L),
        Gibibit("Gibibit", 1074000000),
        Gigabit("Gbit", 1000000000),
        Mebibit("Mebibit", 1049000),
        Megabit("Mbit", 1000000),
        Kibibit("Kibibit", 1024),
        Kilobit("Kbit", 1000),
        Bit("Bit", 1);

        private final String name;
        private final long ratio;

        DataStorage(String name, long ratio) {
            this.name = name;
            this.ratio = ratio;
        }

        public long getRatio() {
            return this.ratio;
        }

    }


    public static String DEFAULT_CB_VALUE = "Choose value";

    @FXML
    private ComboBox<String> fromUnit;

    @FXML
    private ComboBox<String> toUnit;

    @FXML
    private ToggleGroup myToggleGroup;

    @FXML
    private TextField textFieldTo;

    @FXML
    private TextField textFieldFrom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setValuesToComboBox(DataTransfer.class, fromUnit);
        setValuesToComboBox(DataTransfer.class, toUnit);

        textFieldFrom.textProperty().addListener(
                new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    if (newValue.matches("\\d*")) {
                        int value = Integer.parseInt(newValue);
                    } else {
                        textFieldFrom.setText(oldValue);
                    }
                }
                calculateResult();
            }
        });

        myToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (myToggleGroup.getSelectedToggle() != null) {
                    Toggle selectedToggle = myToggleGroup.getSelectedToggle();

                    //reload form
                    textFieldFrom.setText("");
                    textFieldTo.setText("");

                    fromUnit.getItems().remove(0, fromUnit.getItems().size());
                    toUnit.getItems().remove(0, toUnit.getItems().size());

                    switch (myToggleGroup.getToggles().indexOf(selectedToggle)) {

                        case 0:
                            setValuesToComboBox(DataTransfer.class, fromUnit);
                            setValuesToComboBox(DataTransfer.class, toUnit);
                            break;
                        case 1:
                            setValuesToComboBox(DataStorage.class, fromUnit);
                            setValuesToComboBox(DataStorage.class, toUnit);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
        );

        toUnit.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {
                calculateResult();
            }
        });

        fromUnit.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {
                calculateResult();
            }
        });

    }

    public void calculateResult() {

        if (textFieldFrom.getText() != null && toUnit.getSelectionModel().getSelectedItem() != null && fromUnit.getSelectionModel().getSelectedItem() != null) {

            if (!textFieldFrom.getText().equals("") && !toUnit.getSelectionModel().getSelectedItem().equals(DEFAULT_CB_VALUE) && !fromUnit.getSelectionModel().getSelectedItem().equals(DEFAULT_CB_VALUE)) {

       
                BigDecimal num1 = new BigDecimal(1);
                BigDecimal num2 = new BigDecimal(1);
                BigDecimal ratio = new BigDecimal(textFieldFrom.getText());

                switch (myToggleGroup.getToggles().indexOf(myToggleGroup.getSelectedToggle())) {

                    case 0:

                        num1 = new BigDecimal(DataTransfer.valueOf(fromUnit.getSelectionModel().getSelectedItem()).getRatio());
                        num2 = new BigDecimal(DataTransfer.valueOf(toUnit.getSelectionModel().getSelectedItem()).getRatio());

                        break;
                    case 1:
                        num1 = new BigDecimal(DataStorage.valueOf(fromUnit.getSelectionModel().getSelectedItem()).getRatio());
                        num2 = new BigDecimal(DataStorage.valueOf(toUnit.getSelectionModel().getSelectedItem()).getRatio());

                        break;
                    default:
                        break;
                }
                BigDecimal result = num1.divide(num2,3, RoundingMode.HALF_UP).multiply(ratio);

                textFieldTo.setText(result.toString());

              
            }
        }
    }

    public static <E extends Enum<E>> void setValuesToComboBox(Class<E> enumData, ComboBox cb) {
        for (Enum<E> enumVal : enumData.getEnumConstants()) {
            cb.getItems().addAll(enumVal.toString());
        }
        cb.setValue(DEFAULT_CB_VALUE);
    }
}
