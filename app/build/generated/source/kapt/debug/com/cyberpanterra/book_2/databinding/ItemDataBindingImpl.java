package com.cyberpanterra.book_2.databinding;
import com.cyberpanterra.book_2.R;
import com.cyberpanterra.book_2.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemDataBindingImpl extends ItemDataBinding implements com.cyberpanterra.book_2.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemDataBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ItemDataBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[0]
            , (android.widget.TextView) bindings[1]
            , (android.view.View) bindings[2]
            , (android.widget.TextView) bindings[3]
            );
        this.item.setTag(null);
        this.nameText.setTag(null);
        this.separatorView.setTag(null);
        this.valueText.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.cyberpanterra.book_2.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.searchText == variableId) {
            setSearchText((java.lang.String) variable);
        }
        else if (BR.click == variableId) {
            setClick((com.cyberpanterra.book_2.interfaces.OnClickListener) variable);
        }
        else if (BR.data == variableId) {
            setData((com.cyberpanterra.book_2.datas.Data) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setSearchText(@Nullable java.lang.String SearchText) {
        this.mSearchText = SearchText;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.searchText);
        super.requestRebind();
    }
    public void setClick(@Nullable com.cyberpanterra.book_2.interfaces.OnClickListener Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    public void setData(@Nullable com.cyberpanterra.book_2.datas.Data Data) {
        this.mData = Data;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.data);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String searchText = mSearchText;
        boolean dataNameEmptyBooleanTrueDataValueEmpty = false;
        int dataNameEmptyBooleanTrueDataValueEmptyViewGONEViewVISIBLE = 0;
        boolean dataNameEmpty = false;
        java.lang.String dataName = null;
        com.cyberpanterra.book_2.interfaces.OnClickListener<?> click = mClick;
        com.cyberpanterra.book_2.datas.Data data = mData;
        boolean dataValueEmpty = false;
        java.lang.String dataValue = null;

        if ((dirtyFlags & 0xdL) != 0) {



                if (data != null) {
                    // read data.name
                    dataName = data.getName();
                    // read data.value
                    dataValue = data.getValue();
                }

            if ((dirtyFlags & 0xcL) != 0) {

                    if (dataName != null) {
                        // read data.name.empty
                        dataNameEmpty = dataName.isEmpty();
                    }
                if((dirtyFlags & 0xcL) != 0) {
                    if(dataNameEmpty) {
                            dirtyFlags |= 0x20L;
                    }
                    else {
                            dirtyFlags |= 0x10L;
                    }
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x10L) != 0) {

                if (dataValue != null) {
                    // read data.value.empty
                    dataValueEmpty = dataValue.isEmpty();
                }
        }

        if ((dirtyFlags & 0xcL) != 0) {

                // read data.name.empty ? true : data.value.empty
                dataNameEmptyBooleanTrueDataValueEmpty = ((dataNameEmpty) ? (true) : (dataValueEmpty));
            if((dirtyFlags & 0xcL) != 0) {
                if(dataNameEmptyBooleanTrueDataValueEmpty) {
                        dirtyFlags |= 0x80L;
                }
                else {
                        dirtyFlags |= 0x40L;
                }
            }


                // read data.name.empty ? true : data.value.empty ? View.GONE : View.VISIBLE
                dataNameEmptyBooleanTrueDataValueEmptyViewGONEViewVISIBLE = ((dataNameEmptyBooleanTrueDataValueEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
        }
        // batch finished
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.item.setOnClickListener(mCallback1);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            com.cyberpanterra.book_2.interactions.StaticClass.setHighLightedText(this.nameText, dataName, searchText, getColorFromResource(nameText, R.color.color_of_searching));
            com.cyberpanterra.book_2.interactions.StaticClass.setHighLightedText(this.valueText, dataValue, searchText, getColorFromResource(valueText, R.color.color_of_searching));
        }
        if ((dirtyFlags & 0xcL) != 0) {
            // api target 1

            this.separatorView.setVisibility(dataNameEmptyBooleanTrueDataValueEmptyViewGONEViewVISIBLE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // click
        com.cyberpanterra.book_2.interfaces.OnClickListener click = mClick;
        // data
        com.cyberpanterra.book_2.datas.Data data = mData;
        // click != null
        boolean clickJavaLangObjectNull = false;



        clickJavaLangObjectNull = (click) != (null);
        if (clickJavaLangObjectNull) {



            click.onClick(data);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): searchText
        flag 1 (0x2L): click
        flag 2 (0x3L): data
        flag 3 (0x4L): null
        flag 4 (0x5L): data.name.empty ? true : data.value.empty
        flag 5 (0x6L): data.name.empty ? true : data.value.empty
        flag 6 (0x7L): data.name.empty ? true : data.value.empty ? View.GONE : View.VISIBLE
        flag 7 (0x8L): data.name.empty ? true : data.value.empty ? View.GONE : View.VISIBLE
    flag mapping end*/
    //end
}