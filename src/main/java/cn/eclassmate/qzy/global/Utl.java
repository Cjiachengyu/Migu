package cn.eclassmate.qzy.global;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

public class Utl
{
    private static final Logger logger = LogManager.getLogger();

    // time
    // --------------------------------------------------------------------------------
    public static int currentSeconds()
    {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public static String getTimeString(String yyyy_MM_dd__HH_mm_ss)
    {
        return (new SimpleDateFormat(yyyy_MM_dd__HH_mm_ss)).format(System.currentTimeMillis());
    }

    public static String getTimeString(String yyyy_MM_dd__HH_mm_ss, int utfSeconds)
    {
        if (utfSeconds > 1)
        {
            return (new SimpleDateFormat(yyyy_MM_dd__HH_mm_ss)).format(Long.valueOf(utfSeconds) * 1000);
        }
        else
        {
            return "";
        }
    }

    public static String getTimeString_MM_dd_HH_mm(int utfSeconds)
    {
        if (utfSeconds > 1)
        {
            return Cst.SDF_MM_dd_HH_mm.format(Long.valueOf(utfSeconds) * 1000);
        }
        else
        {
            return "";
        }
    }

    public static String getTimeString_MM_dd(int utfSeconds)
    {
        if (utfSeconds > 1)
        {
            return Cst.SDF_MM_dd.format(Long.valueOf(utfSeconds) * 1000);
        }
        else
        {
            return "";
        }
    }

    /**
     * 获取本周一0点的时间
     */
    public static int getBeginTimeOfThisWeek()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        int current = currentSeconds();
        int weekSec = 7 * 24 * 3600;
        int mondaySecond = (int) (calendar.getTimeInMillis() / 1000) + weekSec;
        while (mondaySecond > current)
        {
            mondaySecond = mondaySecond - weekSec;
        }

        return mondaySecond;
    }

    public static int getZeroTimeToday()
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return (int) (c.getTime().getTime() / 1000L);
    }

    // math
    // --------------------------------------------------------------------------------
    public synchronized static long getNextLongId()
    {
        return longId++;
    }

    /**
     * getPercent(20, 30, 2) => 66.67
     *
     * @param d1
     * @param d2
     * @param precision
     * @return
     */
    public static double getPercent(double d1, double d2, int precision)
    {
        if (d1 == 0)
        {
            return 0;
        }

        double base = 1.0;
        for (int i = 0; i < precision; i++)
        {
            base *= 10.0;
        }

        int thousandTimes = (int) Math.rint(d1 * 100.0 * base / d2);
        return thousandTimes / base;
    }

    /**
     * roundDouble(1.23456789, 4) => 1.2346
     *
     * @param d
     * @param precision
     * @return
     */
    public static double roundDouble(double d, int precision)
    {
        double base = 1.0;
        for (int i = 0; i < precision; i++)
        {
            base *= 10.0;
        }

        int baseTimes = (int) Math.rint(d * base);
        return baseTimes / base;
    }

    // string
    // --------------------------------------------------------------------------------
    public static boolean stringEmpty(String s)
    {
        return s == null || s.trim().equals("");
    }

    public static boolean stringNotEmpty(String s)
    {
        return s != null && !s.trim().equals("");
    }

    /**
     * trimEnd("hello, world", 3) => "hello, wo"
     *
     * @param src
     * @param count
     * @return
     */
    public static String trimEnd(String src, int count)
    {
        if (src.length() > 0 && src.length() - count >= 0)
        {
            return src.substring(0, src.length() - count);
        }
        else
        {
            return src;
        }
    }

    public static String getFileSuffix(String fileName)
    {
        String fileSuffix = "";
        int indexDot = fileName.lastIndexOf(".");
        if (indexDot != -1)
        {
            fileSuffix = fileName.substring(indexDot + 1);
        }
        return fileSuffix.toLowerCase();
    }

    public static String getRandomString(int length)
    {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++)
        {
            int number = random.nextInt(charBase.length());
            sb.append(charBase.charAt(number));
        }
        return sb.toString();
    }

    public static List<Integer> splitIdList(String source, String sep)
    {
        List<Integer> sList = new ArrayList<Integer>();
        String[] sArr = source.split(sep);
        for (String s : sArr)
        {
            if (s != null && !s.trim().equals(""))
            {
                sList.add(Integer.parseInt(s.trim()));
            }
        }
        return sList;
    }

    public static String validateUtf8(String inputString)
    {
        if (inputString == null || "".equals(inputString))
        {
            return "";
        }

        char current;
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < inputString.length(); i++)
        {
            current = inputString.charAt(i);
            if ((current == 0x9) ||
                    (current == 0xA) ||
                    (current == 0xD) ||
                    ((current >= 0x20) && (current <= 0xD7FF)) ||
                    ((current >= 0xE000) && (current <= 0xFFFD)) ||
                    ((current >= 0x10000) && (current <= 0x10FFFF)))
            {
                out.append(current);
            }
        }

        return out.toString();
    }

    // file
    // --------------------------------------------------------------------------------
    public static boolean fileExist(String filePath)
    {
        return new File(filePath).exists();
    }

    public static File makeSureDirExists(String dirPath)
    {
        File dir = new File(dirPath);
        if (!dir.exists())
        {
            dir.mkdirs();
        }

        return dir;
    }

    public static File makeSureFileExist(String filePath) throws IOException
    {
        File file = new File(filePath);
        if (file.exists())
        {
            return file;
        }

        makeSureDirExists(file.getParent());

        if (!file.exists())
        {
            file.createNewFile();
        }

        return file;
    }

    public static byte[] readFileAllBytes(String filePath) throws IOException
    {
        InputStream in = null;
        try
        {
            File file = new File(filePath);
            in = new FileInputStream(file);

            int fileLen = (int) file.length();
            int leftToDo = fileLen;
            byte[] ret = new byte[fileLen];
            while (leftToDo > 0)
            {
                int oneRead = in.read(ret, fileLen - leftToDo, leftToDo);
                leftToDo -= oneRead;
            }

            return ret;
        }
        finally
        {
            safeClose(in);
        }
    }

    public static String readFileAllText(String filePath, String charsetName) throws IOException
    {
        return new String(readFileAllBytes(filePath), charsetName);
    }

    public static String readFileAllText(String filePath) throws IOException
    {
        return readFileAllText(filePath, "UTF-8");
    }

    public static String[] readFileAllLines(String filePath) throws IOException
    {
        String allStr = readFileAllText(filePath);
        allStr = allStr.replaceAll("\r\n", "\n");

        return allStr.split("[\r\n]");
    }

    public static void writeFileAllBytes(String filePath, byte[] data, int startPosition) throws IOException
    {
        File f = makeSureFileExist(filePath);
        RandomAccessFile raf = null;
        try
        {
            raf = new RandomAccessFile(f, "rw");
            raf.seek(startPosition);
            raf.write(data);
        }
        finally
        {
            safeClose(raf);
        }
    }

    public static void writeFileAllBytes(String filePath, byte[] data) throws IOException
    {
        File f = new File(filePath);
        if (f.exists())
        {
            f.delete();
        }

        writeFileAllBytes(filePath, data, 0);
    }

    public static void writeFileAllText(String filePath, String content, String charsetName) throws IOException
    {
        writeFileAllBytes(filePath, content.getBytes(charsetName));
    }

    public static void writeFileAllText(String filePath, String content) throws IOException
    {
        writeFileAllBytes(filePath, content.getBytes("UTF-8"));
    }

    public static void copyFile(String srcPath, String desPath) throws IOException
    {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;

        try
        {
            inStream = new FileInputStream(srcPath);
            outStream = new FileOutputStream(desPath);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        }
        finally
        {
            safeClose(inStream);
            safeClose(in);
            safeClose(outStream);
            safeClose(out);
        }
    }

    public static void copyDir(String sourceDir, String targetDir) throws IOException
    {
        File targetDirFile = makeSureDirExists(targetDir);
        File sourceDirFile = new File(sourceDir);

        File[] files = sourceDirFile.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            File file = files[i];
            String sourceFile = sourceDirFile.getAbsolutePath() + File.separator + file.getName();
            String targetFile = targetDirFile.getAbsolutePath() + File.separator + file.getName();

            if (file.isFile())
            {
                copyFile(sourceFile, targetFile);
            }
            else if (file.isDirectory())
            {
                copyDir(sourceFile, targetFile);
            }
        }
    }

    public static void deleteFileOrDir(File file)
    {
        if (file.exists())
        {
            if (file.isFile())
            {
                file.delete();
            }
            else if (file.isDirectory())
            {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++)
                {
                    deleteFileOrDir(files[i]);
                }
                file.delete();
            }
        }
    }

    public static void writeFile(String filePath, InputStream is) throws IOException
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(new File(filePath));
            byte[] buffer = new byte[1024];
            int len;

            while ((len = is.read(buffer)) != -1)
            {
                fos.write(buffer, 0, len);
            }

            fos.flush();
        }
        finally
        {
            safeClose(is);
            safeClose(fos);
        }
    }

    // md5
    // --------------------------------------------------------------------------------
    public static String getMd5(byte[] data)
    {
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        byte[] md5Bytes = md.digest(data);

        BigInteger bigInt = new BigInteger(1, md5Bytes);
        String hashResult = bigInt.toString(16);
        while (hashResult.length() < 32)
        {
            hashResult = "0" + hashResult;
        }

        return hashResult;
    }

    public static String getMd5(String filePath) throws IOException
    {
        byte[] fileData = readFileAllBytes(filePath);

        return getMd5(fileData);
    }

    // gzip
    // --------------------------------------------------------------------------------
    public static byte[] getGzippedBytes(byte[] rawBytes) throws IOException
    {
        ByteArrayOutputStream bos = null;
        GZIPOutputStream gzip = null;
        try
        {
            bos = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(bos);
            gzip.write(rawBytes);
            gzip.flush();
            gzip.finish();

            return bos.toByteArray();
        }
        finally
        {
            safeClose(gzip);
            safeClose(bos);
        }
    }

    // encrypt
    // --------------------------------------------------------------------------------
    public static String base64Encode(byte[] data)
    {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len)
        {
            b1 = data[i++] & 0xff;
            if (i == len)
            {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len)
            {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }

    public static byte[] base64Decode(String str)
            throws UnsupportedEncodingException
    {
        StringBuffer sb = new StringBuffer();
        byte[] data = str.getBytes("US-ASCII");
        int len = data.length;
        int i = 0;
        int b1, b2, b3, b4;
        while (i < len)
        {

            do
            {
                b1 = base64DecodeChars[data[i++]];
            }
            while (i < len && b1 == -1);
            if (b1 == -1)
                break;

            do
            {
                b2 = base64DecodeChars
                        [data[i++]];
            }
            while (i < len && b2 == -1);
            if (b2 == -1)
                break;
            sb.append((char) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

            do
            {
                b3 = data[i++];
                if (b3 == 61)
                    return sb.toString().getBytes("iso8859-1");
                b3 = base64DecodeChars[b3];
            }
            while (i < len && b3 == -1);
            if (b3 == -1)
                break;
            sb.append((char) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

            do
            {
                b4 = data[i++];
                if (b4 == 61)
                    return sb.toString().getBytes("iso8859-1");
                b4 = base64DecodeChars[b4];
            }
            while (i < len && b4 == -1);
            if (b4 == -1)
                break;
            sb.append((char) (((b3 & 0x03) << 6) | b4));
        }
        return sb.toString().getBytes("iso8859-1");
    }

    /**
     * dumpBytesToHexStr(new byte[] { 9, 10, 11, 16, 17 }) -> "|09|0A|0B|10|11"
     *
     * @param byteArray
     * @return
     */
    public static String dumpBytesToHexStr(byte[] byteArray)
    {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++)
        {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    // others
    // --------------------------------------------------------------------------------
    public static void safeClose(Closeable toClose)
    {
        if (toClose != null)
        {
            try
            {
                toClose.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static Properties readFromProperties(String filePath) throws IOException
    {
        Properties prop = new Properties();
        InputStream fin = null;
        InputStream in = null;
        try
        {
            fin = new FileInputStream(filePath);
            in = new BufferedInputStream(fin);
            prop.load(in);
        }
        finally
        {
            safeClose(fin);
            safeClose(in);
        }

        return prop;
    }

    // http
    // --------------------------------------------------------------------------------
    public static String https(String url) throws Exception
    {
        if (url == null || url.length() == 0)
        {
            return null;
        }

        InputStream is = null;
        try
        {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new MyX509TrustManager() }, new java.security.SecureRandom());
            HttpsURLConnection conn = (HttpsURLConnection) (new URL(url)).openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();

            is = conn.getInputStream();

            byte[] uploadedBytes = new byte[conn.getContentLength()];
            int offset = 0;
            while (offset < uploadedBytes.length)
            {
                int oneRead = is.read(uploadedBytes, offset, uploadedBytes.length - offset);
                offset += oneRead;
            }

            logger.debug("bytes from input stream (dumped to hex): " + dumpBytesToHexStr(uploadedBytes));

            return new String(uploadedBytes, "UTF-8");
        }
        finally
        {
            Utl.safeClose(is);
        }
    }

    public static String getRequestParamsString(HttpServletRequest request, String seperator)
    {
        StringBuilder sb = new StringBuilder();

        Enumeration<String> pNames = request.getParameterNames();
        while (pNames.hasMoreElements())
        {
            String key = pNames.nextElement().trim();
            String val = request.getParameter(key).trim();

            sb.append(key + "=" + val + seperator);
        }

        return sb.toString();
    }

    public static String getRequestHeadersString(HttpServletRequest request, String seperator)
    {
        StringBuilder sb = new StringBuilder();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements())
        {
            String key = headerNames.nextElement().trim();
            String val = request.getHeader(key).trim();

            sb.append(key + ":" + val + seperator);
        }

        return sb.toString();
    }

    // implement
    // --------------------------------------------------------------------------------
    private static char[] base64EncodeChars = new char[] {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/' };
    private static byte[] base64DecodeChars = new byte[] {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
            -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
            -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
    private static long longId = System.currentTimeMillis() * 2;
    private static String charBase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

    private static class MyX509TrustManager implements X509TrustManager
    {
        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
        {
            // TODO Auto-generated method stub
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
        {
            // TODO Auto-generated method stub
        }

        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier
    {
        public boolean verify(String hostname, SSLSession session)
        {
            return true;
        }
    }

    private static String byteToHexStr(byte mByte)
    {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0x0F];
        tempArr[1] = Digit[mByte & 0x0F];

        String s = new String(tempArr);
        return "|" + s;
    }

}
