package cn.eclassmate.qzy.viewmodel.subview;

import java.io.Serializable;

public class PageModule implements Serializable
{
    private static final long serialVersionUID = -8876281821546872842L;

    // fields
    // --------------------------------------------------------------------------------
    private int pageSize = 1;
    private int pageCount = 1;
    private int currentPage = 1;

    // constructor
    // --------------------------------------------------------------------------------
    public PageModule(int pageSize)
    {
        this.pageSize = pageSize;
        gotoPage(1);
    }

    // methods
    // --------------------------------------------------------------------------------
    public void gotoPage(int pageNum)
    {
        if (pageNum > pageCount)
        {
            pageNum = pageCount;
        }

        currentPage = pageNum;
    }

    public void changeItemsCount(int itemsCount)
    {
        if (itemsCount <= 0)
        {
            pageCount = 1;
            return;
        }

        pageCount = ((itemsCount - 1) / pageSize) + 1;
    }

    public int getPageSize()
    {
        return this.pageSize;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public int getLimitBegin()
    {
        return (currentPage - 1) * pageSize;
    }

}
