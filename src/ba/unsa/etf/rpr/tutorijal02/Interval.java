package ba.unsa.etf.rpr.tutorijal02;

import java.util.Objects;

public class Interval {
    private double pocetnaTacka;
    private double krajnjaTacka;
    private boolean pocetnaPripadaIntervalu;
    private boolean krajnjaPripadaIntervalu;

    public Interval(double pocetnaTacka, double krajnjaTacka, boolean pocetnaPripadaIntervalu, boolean krajnjaPripadaIntervalu)
            throws IllegalArgumentException {
        if(krajnjaTacka < pocetnaTacka) throw new IllegalArgumentException("Nevalidan interval");

        this.pocetnaTacka = pocetnaTacka;
        this.krajnjaTacka = krajnjaTacka;
        this.pocetnaPripadaIntervalu = pocetnaPripadaIntervalu;
        this.krajnjaPripadaIntervalu = krajnjaPripadaIntervalu;
    }

    //izuzetak konstruktor
    public Interval() {
        pocetnaTacka = 0;
        krajnjaTacka = 0;
        pocetnaPripadaIntervalu = false;
        krajnjaPripadaIntervalu = false;
    }

    public boolean isNull() {
        if(pocetnaTacka == 0 && krajnjaTacka == 0 && !pocetnaPripadaIntervalu && !krajnjaPripadaIntervalu) return true;
        return false;
    }

    public boolean isIn(double tacka) {
        if((tacka > pocetnaTacka && tacka < krajnjaTacka) || (tacka == pocetnaTacka && pocetnaPripadaIntervalu) ||
                (tacka == krajnjaTacka && krajnjaPripadaIntervalu)) return true;
        return false;
    }

    public Interval intersect(Interval drugi) {
        //poredimo pocetne i krajnje tacke i onda uzimamo manju krajnju i vecu pocetnu
        //treba ukljuciti i provjeru da li se pocetne/krajnje tacke nalaze unutar intervala
        double pocetnaTacka = 0;
        double krajnjaTacka = 0;
        boolean pocetnaPripadaIntervalu = false;
        boolean krajnjaPripadaIntervalu = false;

        if(this.pocetnaTacka >= drugi.pocetnaTacka) pocetnaTacka = this.pocetnaTacka;
        else pocetnaTacka = drugi.pocetnaTacka;

        if(this.krajnjaTacka <= drugi.krajnjaTacka) krajnjaTacka = this.krajnjaTacka;
        else krajnjaTacka = drugi.krajnjaTacka;

        if((this.pocetnaTacka == pocetnaTacka && this.pocetnaPripadaIntervalu) || (drugi.pocetnaTacka == pocetnaTacka && drugi.pocetnaPripadaIntervalu)) {
            pocetnaPripadaIntervalu = true;
        }

        if((this.krajnjaTacka == krajnjaTacka && this.krajnjaPripadaIntervalu) || (drugi.krajnjaTacka == krajnjaTacka && drugi.krajnjaPripadaIntervalu)) {
            krajnjaPripadaIntervalu = true;
        }

        return new Interval(pocetnaTacka, krajnjaTacka, pocetnaPripadaIntervalu, krajnjaPripadaIntervalu);

    }

    public static Interval intersect(Interval prvi, Interval drugi) {
        return prvi.intersect(drugi);
    }

    @Override
    public String toString() {

        if(this.isNull()) return "()";

        String vracamo = "";
        if(pocetnaPripadaIntervalu) vracamo = vracamo + "[";
        else vracamo = vracamo + "(";
        vracamo = vracamo + String.valueOf(pocetnaTacka) + "," + String.valueOf(krajnjaTacka);
        if(krajnjaPripadaIntervalu) vracamo = vracamo + "]";
        else vracamo = vracamo + ")";
        return vracamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.pocetnaTacka, pocetnaTacka) == 0 &&
                Double.compare(interval.krajnjaTacka, krajnjaTacka) == 0 &&
                pocetnaPripadaIntervalu == interval.pocetnaPripadaIntervalu &&
                krajnjaPripadaIntervalu == interval.krajnjaPripadaIntervalu;
    }

}
