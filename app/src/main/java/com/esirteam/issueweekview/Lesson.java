package com.esirteam.issueweekview;

import com.alamkanak.weekview.WeekViewDisplayable;
import com.alamkanak.weekview.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by thoma on 04/10/2016.
 */

public class Lesson implements WeekViewDisplayable<Lesson> {
    public static final String FIELD_URL_EDT = "mUrlEDT";
    public static final String FIELD_UID = "mUID";
    public static final String FIELD_VISIBLE = "mVisible";
    public static final String FIELD_DEBUT = "mDebut";
    // TODO CHANGE NAME TO SUMMARY
    public static final String FIELD_MATIERE = "mMatiere";

    private static final SimpleDateFormat simpleHourFormater = new SimpleDateFormat("H:mm", Locale.FRANCE);
    private static final SimpleDateFormat simpleDateFormater = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

    private String mUID;
    private String mMatiere;
    private String mBatiment;
    private String mSalle;

    private String mUrlEDT;

    public String getUrlEDT() {
        return mUrlEDT;
    }

    private int mColor;

    private Date mDebut, mFin;

    private boolean mVisible;

    public Date getDebut() {
        return mDebut;
    }

    public Date getFin() {
        return mFin;
    }

    public void setVisible(boolean visible) {
        mVisible = visible;
    }

    private String mDescription;

    public Lesson(String UID, String matiere, String batiment, String salle, String description, String urlEDT,
                  Calendar calDebut, Calendar calFin, int color) {
        mDebut = calDebut.getTime();
        mFin = calFin.getTime();

        mMatiere = matiere;
        mBatiment = batiment;
        mSalle = salle;
        mUID = UID;
        mDescription = description;
        mUrlEDT = urlEDT;
        mVisible = true;
        mColor = color;
    }

    public Lesson(String UID, String matiere, String batiment, String salle, String description, String urlEDT,
                  int jour, int mois, int annee, int hourDebut, int minDebut, int hourFin, int minFin, int color) {
        Calendar calDebut = Calendar.getInstance();
        calDebut.set(Calendar.YEAR, annee);
        calDebut.set(Calendar.MONTH, mois - 1);
        calDebut.set(Calendar.DAY_OF_MONTH, jour);
        calDebut.set(Calendar.HOUR_OF_DAY, hourDebut);
        calDebut.set(Calendar.MINUTE, minDebut);
        calDebut.set(Calendar.SECOND, 0);
        calDebut.set(Calendar.MILLISECOND, 0);
        mDebut = calDebut.getTime();

        Calendar calFin = Calendar.getInstance();
        calFin.set(Calendar.YEAR, annee);
        calFin.set(Calendar.MONTH, mois - 1);
        calFin.set(Calendar.DAY_OF_MONTH, jour);
        calFin.set(Calendar.HOUR_OF_DAY, hourFin);
        calFin.set(Calendar.MINUTE, minFin);
        calFin.set(Calendar.SECOND, 0);
        calFin.set(Calendar.MILLISECOND, 0);
        mFin = calFin.getTime();

        mMatiere = matiere;
        mBatiment = batiment;
        mSalle = salle;
        mUID = UID;
        mDescription = description;
        mUrlEDT = urlEDT;
        mVisible = true;
        mColor = color;
    }

    public Lesson() {
    }


    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isVisible() {
        return mVisible;
    }

    public String getStringHeureDebut() {
        return simpleHourFormater.format(getDebut());
    }

    public String getStringDate() {
        return simpleDateFormater.format(getDebut());
    }

    public String getStringHeureFin() {
        return simpleHourFormater.format(getFin());
    }

    // TODO ADD TRANSLATE
    public String getTimeEasyDisplayed() {
        return "De " + getStringHeureDebut() + " à " +
                getStringHeureFin();
    }

    public String getTimeAdvancedDisplayed() {
        return "Le " + getStringDate() + " de " + getStringHeureDebut() + " à " +
                getStringHeureFin();
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public Calendar getDebutCalendar() {
        Calendar calStartTime = Calendar.getInstance();
        calStartTime.setTime(mDebut);
        return calStartTime;
    }

    public Calendar getFinCalendar() {
        Calendar calEndTime = Calendar.getInstance();
        calEndTime.setTime(mFin);
        return calEndTime;
    }

    public String getMatiere() {
        return mMatiere;
    }

    public String getLocation() {
        return mBatiment + ":" + mSalle;
    }

    public String getInformations() {
        String rst = "";
        if (mBatiment.equals("") && mSalle.equals("")) {
            rst = mDescription + " - " + mMatiere + " - Emplacement inconnu";
        } else if (mBatiment.equals("") || mSalle.equals("")) {
            rst = mDescription + " - " + mMatiere + " - " + (mBatiment.equals("") ? "" : mBatiment) + (mSalle.equals(
                    "") ? "" : mSalle);

        } else {
            rst = mDescription + " - " + mMatiere + " - " + mBatiment + " - " + mSalle;
        }
        rst += getTimeEasyDisplayed();
        return rst;
    }

    public String getUID() {
        return mUID;
    }

    @Override
    public WeekViewEvent<Lesson> toWeekViewEvent() {
        WeekViewEvent.Style style = new WeekViewEvent.Style.Builder()
                .setBackgroundColor(getColor())
                .setTextStrikeThrough(false)
                .build();

        return new WeekViewEvent.Builder<>(this)
                .setId(0)
                .setTitle(getMatiere())
                .setStartTime(getDebutCalendar())
                .setEndTime(getFinCalendar())
                .setLocation(getLocation())
                .setAllDay(false)
                .setStyle(style)
                .build();
    }
}
