package com.jay.cmbc.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/12/12.
 */
public class CmbcHttpServletResponseWrapper extends HttpServletResponseWrapper {

    StringBuilder stringBuilder;

    public CharArrayWriter getCharArrayWriter() {
        return charArrayWriter;
    }

    private CharArrayWriter charArrayWriter=new CharArrayWriter();

    public CmbcHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        //ServletOutputStream outputStream = response.getOutputStream();

    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int b) throws IOException {

            }

            @Override
            public void write(byte b[], int off, int len) throws IOException {
                stringBuilder.append(new String(b, "UTF-8"));
            }
        };
    }

    @Override
    public PrintWriter getWriter() throws IOException {

        return new PrintWriter("xxxxxxxxxxxxxxxxxxxxxxx");
    }
}
