package editor;

import java.awt.*;

/**
 */
public class LabScheme extends Scheme
{
  static final Color COLOR_BREAKPOINT = new Color( 255, 0, 0, 64 );
  static final Color COLOR_EXECPOINT = new Color( 0, 255, 0, 64 );

  @Override
  Color breakpointColor()
  {
    return COLOR_BREAKPOINT;
  }

  @Override
  Color getExecBreakpoint()
  {
    return COLOR_EXECPOINT;
  }
}