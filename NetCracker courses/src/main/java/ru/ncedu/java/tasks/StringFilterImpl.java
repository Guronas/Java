package ru.ncedu.java.tasks;

import java.util.*;

/**
 * Created by Maksim Frolov on 07.04.2016.
 */

public class StringFilterImpl implements StringFilter {
    public StringFilterImpl() {
    }

    private Set<String> mainCollection = new HashSet<>();

    private Iterator<String> getIter(final Filter f) {
        return new Iterator<String>() {
            String s = null;
            Iterator<String> iter = mainCollection.iterator();

            @Override
            public boolean hasNext() {
                if (mainCollection.size() > 0) {
                    while (iter.hasNext()) {
                        s = iter.next();
                        if (s != null && f.check(s)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public String next() {
                return s;
            }

            @Override
            public void remove() {
            }
        };
    }

    @Override
    public void add(String s) {
        if (s != null) {
            mainCollection.add(s.toLowerCase());
        } else mainCollection.add(s);
    }

    @Override
    public boolean remove(String s) {
        return mainCollection.remove(s);
    }

    @Override
    public void removeAll() {
        Iterator<String> iter = mainCollection.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
    }

    @Override
    public Collection<String> getCollection() {
        return mainCollection;
    }

    @Override
    public Iterator<String> getStringsContaining(final String chars) {
        Filter filter = new Filter() {
            @Override
            public boolean check(String s) {
                return chars == null || chars.isEmpty() || s.contains(chars.toLowerCase());
            }
        };

        return getIter(filter);
    }

    @Override
    public Iterator<String> getStringsStartingWith(final String begin) {
        Filter filter = new Filter() {
            @Override
            public boolean check(String s) {
                return begin == null || begin.isEmpty() || s.startsWith(begin.toLowerCase());
            }
        };

        return getIter(filter);
    }

    @Override
    public Iterator<String> getStringsByNumberFormat(final String format) {
        Filter filter = new Filter() {
            @Override
            public boolean check(String s) {
                if (format == null || format.isEmpty()) {
                    return true;
                } else {
                    for (int i = 0; i < format.length(); i++) {
                        if (format.length() != s.length()) {
                            return false;
                        } else if ((format.charAt(i) == '#' && !Character.isDigit(s.charAt(i))) ||
                                (format.charAt(i) != '#' && format.charAt(i) != s.charAt(i))) {
                            return false;
                        }
                    }
                }
                return true;
            }
        };

        return getIter(filter);
    }

    @Override
    public Iterator<String> getStringsByPattern(final String pattern) {
        Filter filter = new Filter() {
            @Override
            public boolean check(String s) {
                if (pattern == null || pattern.isEmpty()) {
                    return true;
                } else {
                    if (pattern.charAt(0) == '*' && pattern.charAt(pattern.length() - 1) != '*') {
                        return s.endsWith(pattern.substring(1));
                    } else if (pattern.charAt(pattern.length() - 1) == '*' && pattern.charAt(0) != '*') {
                        return s.startsWith(pattern.substring(0, pattern.length() - 1));
                    } else if (pattern.charAt(pattern.length() - 1) == '*' && pattern.charAt(0) == '*') {
                        return s.contains(pattern.substring(1, pattern.length() - 1));
                    } else {
                        int ind1 = -1;
                        int ind2 = -1;
                        for (int i = 0; i < pattern.length() - 1; i++) {
                            if (ind1 == -1 && pattern.charAt(i) == '*') {
                                ind1 = i;
                            } else if (ind1 != -1 && pattern.charAt(i) == '*') {
                                ind2 = i;
                            }
                        }
                        if (ind2 == -1) {
                            return s.startsWith(pattern.substring(0, ind1)) && s.endsWith(pattern.substring(ind1 + 1));
                        } else {
                            return s.startsWith(pattern.substring(0, ind1)) &&
                                    s.contains(pattern.substring(ind1 + 1, ind2)) &&
                                    s.endsWith(pattern.substring(ind2 + 1));
                        }
                    }
                }
            }
        };

        return getIter(filter);
    }

    private interface Filter {
        boolean check(String s);
    }
}
