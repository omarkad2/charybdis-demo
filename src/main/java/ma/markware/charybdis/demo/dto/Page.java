package ma.markware.charybdis.demo.dto;

import java.nio.ByteBuffer;
import java.util.List;

public class Page<T> {

  private List<T> results;
  private ByteBuffer pagingState;

  public Page() {
  }

  public List<T> getResults() {
    return results;
  }

  public void setResults(final List<T> results) {
    this.results = results;
  }

  public ByteBuffer getPagingState() {
    return pagingState;
  }

  public void setPagingState(final ByteBuffer pagingState) {
    this.pagingState = pagingState;
  }
}
