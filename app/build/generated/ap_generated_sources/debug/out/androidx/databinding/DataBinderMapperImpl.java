package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.cyberpanterra.book_2.DataBinderMapperImpl());
  }
}
