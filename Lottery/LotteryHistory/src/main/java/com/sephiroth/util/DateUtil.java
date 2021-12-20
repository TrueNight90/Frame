package com.sephiroth.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {
    // 获取当天的开始时间
    public static java.util.Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获取某天的开始时间
    public static java.util.Date getOneDayBegin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获取当天的结束时间
    public static java.util.Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    // 获取某天的结束时间
    public static java.util.Date getOneDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    // 获取某天前一天的开始时间
    public static Date getBeginDayOfDayBefore(Date countDate) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getOneDayBegin(countDate));
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
    // 获取昨天的开始时间
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    // 获取某天前一天的结束时间
    public static Date getEndDayOfDayBefore(Date countDate) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getOneDayEnd(countDate));
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
    // 获取昨天的结束时间
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    // 获取明天的开始时间
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    // 获取明天的结束时间
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    // 获取本周的开始时间
    @SuppressWarnings("unused")
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    // 获取某周的开始时间
    @SuppressWarnings("unused")
    public static Date getBeginDayOfOneWeek(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    // 获取本周的结束时间
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    // 获取某周的结束时间
    public static Date getEndDayOfOneWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfOneWeek(date));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    // 获取上周的开始时间
    @SuppressWarnings("unused")
    public static Date getBeginDayOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal.getTime());
    }

    // 获取上周的结束时间
    public static Date getEndDayOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    // 获取本月的开始时间
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    // 获取某月的开始时间
    public static Date getBeginDayOfOneMonth(Date date) {
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        //设置为第一天
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        gcLast.set(Calendar.HOUR_OF_DAY, 0);
        gcLast.set(Calendar.MINUTE, 0);
        gcLast.set(Calendar.SECOND, 0);

        Date startDate = gcLast.getTime();
        return startDate;
    }

    // 获取本月的结束时间
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    // 获取某月的结束时间
    public static Date getEndDayOfOneMonth(Date date) {
        //获取Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date endDate = calendar.getTime();
        return endDate;

    }

    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Date getMonthBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        date.setTime(c.getTimeInMillis());
        return date;
    }

    /**
     * 获取指定日期所在月份结束的时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        date.setTime(c.getTimeInMillis() * 1000);
        return date;
    }

    // 获取上月的开始时间
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        return getDayStartTime(calendar.getTime());
    }

    // 获取上月的结束时间
    public static Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 2, day);
        return getDayEndTime(calendar.getTime());
    }

    // 获取本年的开始时间
    public static Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }

    // 获取本年的结束时间
    public static java.util.Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    // 获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    // 获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    // 获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    // 获取某年是哪一年
    public static Integer getNowOneYear(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    // 获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    // 获取某月是哪一月
    public static int getNowOneMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    // 两个日期相减得到的天数
    public static int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime())
                / (1000 * 60 * 60 * 24);
        int days = new Long(diff).intValue();
        return days;
    }

    // 两个日期相减得到的毫秒数
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }

    // 获取两个日期中的最大日期
    public static Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return beginDate;
        }
        return endDate;
    }

    // 获取两个日期中的最小日期
    public static Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }

    // 返回某月该季度的第一个月
    public static Date getFirstSeasonDate(Date date) {
        final int[] SEASON = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int sean = SEASON[cal.get(Calendar.MONTH)];
        cal.set(Calendar.MONTH, sean * 3 - 3);
        return cal.getTime();
    }

    // 返回某个日期下几天的日期
    public static Date getNextDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }

    // 返回某个日期前几天的日期
    public static Date getFrontDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }

    // 获取某年某月到某年某月按天的切片日期集合(间隔天数的集合)
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List getTimeList(int beginYear, int beginMonth, int endYear,
                                   int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));
            }
        } else {
            {
                for (int j = beginMonth; j < 12; j++) {
                    list.add(getTimeList(beginYear, j, k));
                }
                for (int i = beginYear + 1; i < endYear; i++) {
                    for (int j = 0; j < 12; j++) {
                        list.add(getTimeList(i, j, k));
                    }
                }
                for (int j = 0; j <= endMonth; j++) {
                    list.add(getTimeList(endYear, j, k));
                }
            }
        }
        return list;
    }

    // 获取某年某月按天切片日期集合(某个月间隔多少天的日期集合)
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }

    /**
     * 根据日期字符串判断当月第几周
     *
     * @return
     * @throws Exception
     */
    public static int getWeek() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //第几周
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        //第几天，从周日开始
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    /**
     * --------------------------- 函数名称：getSystemTime 功能： 取得系统时间 参数： 无 返回值： 返回
     * String 系统时间 -----------------------------
     */
    public static String getSystemTime() {
        String systemTime = null;
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        systemTime = format.format(date).toString();
        return systemTime;
    }

    /**
     * 取得某一天的当前时间 2008-7-4
     *
     * @param num 向前或向后滚动num天，正数向未来，负数向过去
     * @return
     * @author zhaobo
     */
    public static Date getNowOnOneDay(int num) {
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DATE, num);
        if (num > 0) {
            if (cal.getTime().before(new Date())) {
                cal.add(Calendar.MONTH, 1);
            }
            if (cal.getTime().before(new Date())) {
                cal.add(Calendar.YEAR, 1);
            }
        } else if (num < 0) {
            if (cal.getTime().after(new Date())) {
                cal.add(Calendar.MONTH, -1);
            }
            if (cal.getTime().after(new Date())) {
                cal.add(Calendar.YEAR, -1);
            }
        }
        return cal.getTime();
    }

    /**
     * 时间格式化方法 格式:2008-05-20 16:40:40 2008-9-2
     *
     * @param date
     * @return
     * @author zhaobo
     */
    public static String getDateTimeString(Date date) {
        if (date == null)
            return "";
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 时间格式化方法 格式:2011-12-25 12:12:00
     *
     * @param date
     * @return
     * @author zhaobo
     */
    public static String getTimeString(Date date) {
        if (date == null)
            return "";
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 取出月
     *
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getMonthStr(Date date) {
        if (1 == date.getMonth() + 1) {
            return "01";
        } else if (2 == date.getMonth() + 1) {
            return "02";
        } else if (3 == date.getMonth() + 1) {
            return "03";
        } else if (4 == date.getMonth() + 1) {
            return "04";
        } else if (5 == date.getMonth() + 1) {
            return "05";
        } else if (6 == date.getMonth() + 1) {
            return "06";
        } else if (7 == date.getMonth() + 1) {
            return "07";
        } else if (8 == date.getMonth() + 1) {
            return "08";
        } else if (9 == date.getMonth() + 1) {
            return "09";
        }
        return date.getMonth() + 1 + "";
    }

    /**
     * 取出日
     *
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getDayStr(Date date) {
        if (1 == date.getDate()) {
            return "01";
        } else if (2 == date.getDate()) {
            return "02";
        } else if (3 == date.getDate()) {
            return "03";
        } else if (4 == date.getDate()) {
            return "04";
        } else if (5 == date.getDate()) {
            return "05";
        } else if (6 == date.getDate()) {
            return "06";
        } else if (7 == date.getDate()) {
            return "07";
        } else if (8 == date.getDate()) {
            return "08";
        } else if (9 == date.getDate()) {
            return "09";
        }
        return date.getDate() + "";
    }

    /**
     * 取出时
     *
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getHourStr(Date date) {
        if (0 == date.getHours()) {
            return "00";
        } else if (1 == date.getHours()) {
            return "01";
        } else if (2 == date.getHours()) {
            return "02";
        } else if (3 == date.getHours()) {
            return "03";
        } else if (4 == date.getHours()) {
            return "04";
        } else if (5 == date.getHours()) {
            return "05";
        } else if (6 == date.getHours()) {
            return "06";
        } else if (7 == date.getHours()) {
            return "07";
        } else if (8 == date.getHours()) {
            return "08";
        } else if (9 == date.getHours()) {
            return "09";
        }
        return date.getHours() + "";
    }

    /**
     * 取出分
     *
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getMinuteiStr(Date date) {
        if (0 == date.getMinutes()) {
            return "00";
        } else if (1 == date.getMinutes()) {
            return "01";
        } else if (2 == date.getMinutes()) {
            return "02";
        } else if (3 == date.getMinutes()) {
            return "03";
        } else if (4 == date.getMinutes()) {
            return "04";
        } else if (5 == date.getMinutes()) {
            return "05";
        } else if (6 == date.getMinutes()) {
            return "06";
        } else if (7 == date.getMinutes()) {
            return "07";
        } else if (8 == date.getMinutes()) {
            return "08";
        } else if (9 == date.getMinutes()) {
            return "09";
        }
        return date.getMinutes() + "";
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        //设置当前时间
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        //设置开始时间
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        //设置结束时间
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        //处于开始时间之后，和结束时间之前的判断   ! before  不在之前  >= 某时间    &&   ! after  <=  某时间
        if (!date.before(begin) && !date.after(end)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断时间是否在时间之后
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean afterCalendar(Date nowTime, Date beginTime) {
        //设置当前时间
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        //设置开始时间
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        //处于开始时间之后，和结束时间之前的判断   ! before  不在之前  >= 某时间
        if (!date.before(begin)) {
            return true;
        } else {
            return false;
        }
    }

    //Date或者String转化为Unix时间戳
    public static long dateToUnixDate(Date date) {
        //Date或者String转化为时间戳
      /*  SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time="1970-01-06 11:45:55";
        Date date = format.parse(time);
        System.out.print("Format To times:"+date.getTime());*/
        return date.getTime() / 1000;
    }

    /**
     * 将java.util.Date对象转化为java.sql.Timestamp对象
     *
     * @param date 要转化的java.util.Date对象
     * @return 转化后的java.sql.Timestamp对象
     */
    public static java.sql.Timestamp dateToTime(java.util.Date date) {
        String strDate = dateToStr(date, "yyyy-MM-dd HH:mm:ss SSS");
        return strToSqlDate(strDate, "yyyy-MM-dd HH:mm:ss SSS");
    }

    /**
     * 将java.util.Date对象转化为String字符串
     *
     * @param date      要格式的java.util.Date对象
     * @param strFormat 输出的String字符串格式的限定（如："yyyy-MM-dd HH:mm:ss"）
     * @return 表示日期的字符串
     */
    public static String dateToStr(java.util.Date date, String strFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(strFormat);
        String str = sf.format(date);
        return str;
    }

    /**
     * 将String字符串转换为java.sql.Timestamp格式日期,用于数据库保存
     *
     * @param strDate    表示日期的字符串
     * @param dateFormat 传入字符串的日期表示格式（如："yyyy-MM-dd HH:mm:ss"）
     * @return java.sql.Timestamp类型日期对象（如果转换失败则返回null）
     */
    public static java.sql.Timestamp strToSqlDate(String strDate, String dateFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        java.util.Date date = null;

        try {
            date = sf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Timestamp dateSQL = new java.sql.Timestamp(date.getTime());
        return dateSQL;
    }
}


