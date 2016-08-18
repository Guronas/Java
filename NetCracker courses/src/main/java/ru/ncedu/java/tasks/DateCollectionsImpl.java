package ru.ncedu.java.tasks;

import java.text.ParseException;
import java.util.*;
import java.text.DateFormat;

/**
 * Created by Frolov Maksim on 28.03.2016.
 */

public class DateCollectionsImpl implements DateCollections {
    public DateCollectionsImpl() {
    }

    private DateFormat dateStyle = DateFormat.getDateInstance();
    private SortedMap<String, Element> mainMap;

    @Override
    public void setDateStyle(int dateStyle) {
        this.dateStyle = DateFormat.getDateInstance(dateStyle);
    }

    @Override
    public Calendar toCalendar(String dateString) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateStyle.parse(dateString));
        return calendar;
    }

    @Override
    public String toString(Calendar date) {
        return dateStyle.format(date.getTime());
    }

    @Override
    public void initMainMap(int elementsNumber, Calendar firstDate) {
        mainMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i = 0;
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return 1;
                }
                try {
                    i = toCalendar(o1).compareTo(toCalendar(o2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return i;
            }
        });

        Random random = new Random();
        for (int i = 0; i < elementsNumber; i++) {
            Calendar calendar = (Calendar) firstDate.clone();
            calendar.add(Calendar.DATE, i * 110);
            Element element = new Element(calendar, random.nextInt(2000));
            mainMap.put(toString(element.getBirthDate()), element);
        }
    }


    @Override
    public void setMainMap(Map<String, Element> map) {
        mainMap = (SortedMap<String, Element>) map;
    }

    @Override
    public Map<String, Element> getMainMap() {
        return mainMap;
    }

    @Override
    public SortedMap<String, Element> getSortedSubMap() {
        Set<String> keySet = mainMap.keySet();
        String key = null;
        for (String s : keySet) {
            try {
                if (this.toCalendar(s).getTimeInMillis() > Calendar.getInstance().getTimeInMillis()) {
                    key = s;
                    break;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return mainMap.tailMap(key);
    }

    @Override
    public List<Element> getMainList() {
        List<Element> list = new LinkedList<>();
        for (Element value : mainMap.values()) {
            list.add(value);
        }
        return list;
    }

    @Override
    public void sortList(List<Element> list) {
        Collections.sort(list, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                int i = 0;
                i = o1.getDeathDate().compareTo(o2.getDeathDate());
                return i;
            }
        });
    }

    @Override
    public void removeFromList(List<Element> list) {
        for (Iterator<Element> iter = list.iterator(); iter.hasNext(); ) {
            switch (iter.next().getBirthDate().get(Calendar.MONTH) + 1) {
                case 1:
                case 2:
                case 12:
                    iter.remove();
                    break;
            }
        }
    }
}
