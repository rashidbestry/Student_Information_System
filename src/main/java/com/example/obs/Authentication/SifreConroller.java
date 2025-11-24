package com.example.obs.Authentication;

public class SifreConroller {

    public String DataBase_File_ogrenci(String ogr_no ){ // burda txt dosyasına gidip gerekli bilgiyi getirecek
        return "1234" ; // şifreyi return edecek
    }

    public String DataBase_File_akademisyen(String kullanici_adi ){
        return "1234" ;
    }

    public boolean sifre_kontrol_ogrenci(String input_numara ,String input_sifre){
        // text dosyasına git ogrenci numarasını bul ona karşılık gelen şifreyi getir ve inputta gelen şifreyi karşılaştır
        String temp = DataBase_File_ogrenci(input_numara);
        if(temp.equals(input_sifre)){
            return true;
        }
        else return false;
    }
    public boolean sifre_kontrol_akademisyen(String input_kullaniciAdi ,String input_sifre){
        return true;
    }


}

