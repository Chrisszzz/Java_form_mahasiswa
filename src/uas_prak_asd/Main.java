package uas_prak_asd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    TableView<DataMahasiswa> tabel;
    TextField txtNim;
    TextField txtNama;
    TextField txtAlamat;
    TextField txtRt;
    TextField txtRw;
    TextField txtKota;
    TextField txtAgama;
    TextField txtGender;
    ComboBox<String> comboGrup;
    RadioButton rbMale = new RadioButton("Pria");
    RadioButton rbFemale = new RadioButton("Wanita");
    ToggleGroup toggleGroup = new ToggleGroup();

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Daftar Pendaftaran Mahasiswa");

        TableColumn<DataMahasiswa, String> kolNim = new TableColumn<>("NIM");
        kolNim.setCellValueFactory(new PropertyValueFactory<>("nim"));
        kolNim.setMinWidth(100);
        kolNim.setStyle("-fx-alignment:center");

        TableColumn<DataMahasiswa, String> kolNama = new TableColumn<>("Nama");
        kolNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolNama.setMinWidth(100);
        kolNama.setStyle("-fx-alignment:center");

        TableColumn<DataMahasiswa, Integer> kolAlamat = new TableColumn<>("Alamat");
        kolAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        kolAlamat.setMinWidth(100);
        kolAlamat.setStyle("-fx-alignment:center");

        TableColumn<DataMahasiswa, String> kolRt = new TableColumn<>("Rt");
        kolRt.setCellValueFactory(new PropertyValueFactory<>("rt"));
        kolRt.setMinWidth(20);
        kolRt.setStyle("-fx-alignment:center");

        TableColumn<DataMahasiswa, String> kolRw = new TableColumn<>("Rw");
        kolRw.setCellValueFactory(new PropertyValueFactory<>("rw"));
        kolRw.setMinWidth(20);
        kolRw.setStyle("-fx-alignment:center");

        TableColumn<DataMahasiswa, String> kolKota = new TableColumn<>("Kota");
        kolKota.setCellValueFactory(new PropertyValueFactory<>("kota"));
        kolKota.setMinWidth(50);
        kolKota.setStyle("-fx-alignment:center");

        TableColumn<DataMahasiswa, String> kolAgama = new TableColumn<>("Agama");
        kolAgama.setCellValueFactory(new PropertyValueFactory<>("agama"));
        kolAgama.setMinWidth(50);
        kolAgama.setStyle("-fx-alignment:center");

        TableColumn<DataMahasiswa, String> kolGender = new TableColumn<>("Gender");
        kolGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        kolGender.setMinWidth(50);
        kolGender.setStyle("-fx-alignment:center");

        tabel = new TableView<>();
        tabel.setItems(getDataMahasiswa());
        tabel.getColumns().addAll(kolNim, kolNama, kolAlamat, kolRt, kolRw, kolKota, kolAgama, kolGender);
        HBox hbGender = new HBox(5);
        hbGender.getChildren().addAll(rbMale, rbFemale);

        comboGrup = new ComboBox<>();
        comboGrup.setPromptText("Agama");
        comboGrup.getItems().addAll("Islam", "Kristen", "Katolik", "Hindu", "Budha", "Konghucu");
        comboGrup.setPrefWidth(200);

        txtNim = new TextField();
        txtNim.setPromptText("NIM");

        txtNama = new TextField();
        txtNama.setPromptText("Nama");

        txtAlamat = new TextField();
        txtAlamat.setPromptText("Alamat");

        txtRt = new TextField();
        txtRt.setPromptText("RT");

        txtRw = new TextField();
        txtRw.setPromptText("RW");

        txtKota = new TextField();
        txtKota.setPromptText("Kota");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        // Mengatur kolom untuk tabel
        GridPane.setColumnSpan(tabel, 2);
        GridPane.setRowSpan(tabel, 7);
        gridPane.add(tabel, 0, 7, 2, 4);

        gridPane.add(new Label("NIM"), 0, 0);
        gridPane.add(txtNim, 1, 0);
        gridPane.add(new Label("Nama"), 0, 1);
        gridPane.add(txtNama, 1, 1);
        gridPane.add(new Label("Alamat"), 0, 2);
        gridPane.add(txtAlamat, 1, 2);
        gridPane.add(new Label("RT/RW"), 0, 3);
        gridPane.add(txtRt, 1, 3);
        gridPane.add(txtRw, 2, 3);

        // Tambahkan komponen Kota
        gridPane.add(new Label("Kota"), 0, 4);
        gridPane.add(txtKota, 1, 4);

        // Tambahkan komponen Agama
        gridPane.add(new Label("Agama"), 0, 5);
        gridPane.add(comboGrup, 1, 5);

        // Tambahkan komponen Gender
        gridPane.add(new Label("Jenis Kelamin"), 0, 6);
        gridPane.add(hbGender, 1, 6);

        Button btnAdd = new Button("Create");
        btnAdd.setOnAction(event -> tambahData());

        Button btnUpdate = new Button("Edit");
        btnUpdate.setOnAction(event -> editData());

        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(event -> hapusData());

        Button btnClose = new Button("Close");
        btnClose.setOnAction(event -> primaryStage.close());

        Button btnCari = new Button("Find");
        btnCari.setOnAction(event -> cari());
        Button btnSave = new Button("Save");
        btnSave.setOnAction(event -> save());
        Button btnFilter = new Button("Filter");
        btnFilter.setOnAction(event -> filter());

        Button btnSortAscending = new Button("Sort Ascending");
        btnSortAscending.setOnAction(event -> quickSort(true));

        Button btnSortDescending = new Button("Sort Descending");
        btnSortDescending.setOnAction(event -> quickSort(false));

        HBox hbSortButtons = new HBox(5);
        hbSortButtons.getChildren().addAll(btnSortAscending, btnSortDescending);

        HBox hbButtons = new HBox(5);
        hbButtons.getChildren().addAll(btnAdd, btnUpdate, btnDelete, btnSave, btnCari, btnFilter, hbSortButtons);

        HBox hbCloseButton = new HBox(5);
        hbCloseButton.getChildren().add(btnClose);
        hbCloseButton.setAlignment(Pos.CENTER_RIGHT);

        VBox vb = new VBox(5);
        vb.getChildren().addAll(gridPane, tabel, hbButtons, hbCloseButton);
        vb.setPadding(new Insets(10));

        tabel.setOnMouseClicked(event -> {
            // Mendapatkan baris yang dipilih
            DataMahasiswa selectedRow = tabel.getSelectionModel().getSelectedItem();

            if (selectedRow != null) {
                // Menampilkan data baris yang dipilih pada TextField, RadioButton, dan ComboBox
                txtNim.setText(selectedRow.getNim());
                txtNama.setText(selectedRow.getNama());
                txtAlamat.setText(selectedRow.getAlamat());
                txtRt.setText(selectedRow.getRt());
                txtRw.setText(selectedRow.getRw());
                txtKota.setText(selectedRow.getKota());
                comboGrup.setValue(selectedRow.getAgama());

                // Memeriksa jenis kelamin untuk mengaktifkan RadioButton yang sesuai
                if (selectedRow.getGender().equals("Pria")) {
                    rbMale.setSelected(true);
                } else {
                    rbFemale.setSelected(true);
                }
            }
        });

        Scene scene = new Scene(vb, 720, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Daftar Mahasiswa");
        primaryStage.show();

    }

    public ObservableList<DataMahasiswa> getDataMahasiswa() {
        ObservableList<DataMahasiswa> mhs = FXCollections.observableArrayList();
        return mhs;
    }

    private void tambahData() {
        String nim = txtNim.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String rt = txtRt.getText();
        String rw = txtRw.getText();
        String kota = txtKota.getText();
        String agama = comboGrup.getValue();
        String gender = rbMale.isSelected() ? "Pria" : "Wanita";

        if (nim.isEmpty() || nama.isEmpty() || alamat.isEmpty() || agama == null || rt.isEmpty() || rw.isEmpty() || kota.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Input tidak lengkap");
            alert.setContentText("Silahkan lengkapi data anda terlebih dahulu");
            alert.showAndWait();
            return;
        }

        DataMahasiswa mhs = new DataMahasiswa(nim, nama, alamat, rt, rw, kota, agama, gender);
        tabel.getItems().add(mhs);

        // Reset nilai input
        txtNim.clear();
        txtNama.clear();
        txtAlamat.clear();
        txtRt.clear();
        txtRw.clear();
        txtKota.clear();
        comboGrup.getSelectionModel().clearSelection();
        toggleGroup.selectToggle(null);
    }

    private void editData() {
        DataMahasiswa selectedMaha = tabel.getSelectionModel().getSelectedItem();
        if (selectedMaha != null) {
            String nim = txtNim.getText();
            String nama = txtNama.getText();
            String alamat = txtAlamat.getText();
            String rt = txtRt.getText();
            String rw = txtRw.getText();
            String kota = txtKota.getText();
            String agama = comboGrup.getValue();
            String gender = rbMale.isSelected() ? "Pria" : "Wanita";

            if (nim.isEmpty() || nama.isEmpty() || alamat.isEmpty() || agama == null || rt.isEmpty() || rw.isEmpty() || kota.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input tidak lengkap");
                alert.setContentText("Silakan lengkapi data terlebih dahulu");
                alert.showAndWait();
                return;
            }

            selectedMaha.setNim(nim);
            selectedMaha.setNama(nama);
            selectedMaha.setAlamat(alamat);
            selectedMaha.setRt(rt);
            selectedMaha.setRw(rw);
            selectedMaha.setKota(kota);
            selectedMaha.setAgama(agama);
            selectedMaha.setGender(gender);

            tabel.refresh();

            // Reset nilai input
            txtNim.clear();
            txtNama.clear();
            txtAlamat.clear();
            txtRt.clear();
            txtRw.clear();
            txtKota.clear();
            comboGrup.getSelectionModel().clearSelection();
            toggleGroup.selectToggle(null);
        }
    }

    private void hapusData() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Konfimrasi");
        alert.setHeaderText("Mohon Konfirmasi");
        alert.setContentText("Apakah Anda yakin akan menghapus data ?");
        Optional<ButtonType> jwb = alert.showAndWait();
        if (jwb.get() == ButtonType.OK) {
            System.out.println("Pengguna setuju...");
            ObservableList<DataMahasiswa> selectedItems = tabel.getSelectionModel().getSelectedItems();
            tabel.getItems().removeAll(selectedItems);
        } else {
            System.out.println("Pengguna menolak...");

        }

    }

    String dialogBox() {
        Label lblNim = new Label("Nim Yang Dicari");
        TextField txtNim = new TextField();
        txtNim.setMaxWidth(70);
        txtNim.setPromptText("nim");
        Button btnCari = new Button("_Find");
        HBox hb = new HBox(5, lblNim, txtNim, btnCari);
        hb.setPadding(new Insets(15, 10, 10, 10));
        Stage window = new Stage();
        window.setScene(new Scene(hb));
        window.initModality(Modality.APPLICATION_MODAL);
        btnCari.setOnAction(e -> window.close());
        window.showAndWait();
        return txtNim.getText();
    }

    void cari() {
        String nimCari = dialogBox();
        ObservableList<DataMahasiswa> daftarMahasiswa = tabel.getItems();
        int idx, cacah = daftarMahasiswa.size();
        for (idx = 0; idx < cacah; idx++) {
            if (daftarMahasiswa.get(idx).getNim().equals(nimCari)) {
                break;
            }
        }
        if (idx < cacah) {
            tabel.getSelectionModel().select(idx);
            tabel.scrollTo(idx);
            tabel.requestFocus();
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Ditemukan");
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Nim " + nimCari + " tidak ditemukan!");
            a.show();
        }

    }

    void save() {
        String namaFile = "src/uas_prak_asd/Mahasiswa.csv";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile));

            ObservableList<DataMahasiswa> dataMahasiswa = tabel.getItems();
            for (DataMahasiswa mahasiswa : dataMahasiswa) {
                String line = mahasiswa.getNim() + "," + mahasiswa.getNama() + "," + mahasiswa.getAlamat() + ","
                        + mahasiswa.getRt() + "," + mahasiswa.getRw() + "," + mahasiswa.getKota() + ","
                        + mahasiswa.getAgama() + "," + mahasiswa.getGender();
                writer.write(line);
                writer.newLine();
            }

            writer.close();
            System.out.println("Data berhasil disimpan ke dalam file.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan file: " + e.getMessage());
        }
    }

    private void filter() {
        String cariAgama = comboGrup.getValue();
        if (cariAgama != null) {
            ObservableList<DataMahasiswa> filteredData = getDataMahasiswa().filtered(dataMahasiswa
                    -> dataMahasiswa.getAgama().equals(cariAgama)
            );
            tabel.setItems(filteredData);
        } else {
            tabel.setItems(getDataMahasiswa());
        }
    }

    private void quickSort(boolean ascending) {
        ObservableList<DataMahasiswa> data = tabel.getItems();
        quickSortHelper(data, 0, data.size() - 1, ascending);
        tabel.setItems(data);
    }

    private void quickSortHelper(ObservableList<DataMahasiswa> data, int low, int high, boolean ascending) {
        if (low < high) {
            int pivotIndex = partition(data, low, high, ascending);
            quickSortHelper(data, low, pivotIndex - 1, ascending);
            quickSortHelper(data, pivotIndex + 1, high, ascending);
        }
    }

    private int partition(ObservableList<DataMahasiswa> data, int low, int high, boolean ascending) {
        String pivot = data.get(high).getNim();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            String currentNim = data.get(j).getNim();
            if ((ascending && currentNim.compareTo(pivot) < 0) || (!ascending && currentNim.compareTo(pivot) > 0)) {
                i++;
                swap(data, i, j);
            }
        }

        swap(data, i + 1, high);
        return i + 1;
    }

    private void swap(ObservableList<DataMahasiswa> data, int i, int j) {
        DataMahasiswa temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

}
