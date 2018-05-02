package mimic;

import java.util.List;
import org.junit.jupiter.api.Test;



class TestDiff
{
  TestDiff() {}
  
  @Test
  void test()
  {
    DiffMatch diff = new DiffMatch();
    List<DiffMatch.Diff> diffs = diff.diff_main("<response>53<response>", "<response>52<response>");
    DiffMatch.Diff d = (DiffMatch.Diff)diffs.get(0);
  }
}
