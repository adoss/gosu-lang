/*
 * Copyright 2014 Guidewire Software, Inc.
 */
package gw.internal.gosu.init;

import gw.config.ExecutionMode;
import gw.internal.gosu.parser.ExecutionEnvironment;
import gw.internal.gosu.parser.TypeLoaderAccess;
import gw.lang.gosuc.GosucModule;
import gw.lang.init.GosuPathEntry;
import gw.lang.init.IGosuInitialization;
import gw.lang.reflect.module.IExecutionEnvironment;
import gw.lang.reflect.module.IModule;

import java.util.List;

public class InternalGosuInit implements IGosuInitialization
{
  private static IGosuInitialization INSTANCE;

  private InternalGosuInit() {
  }

  public static IGosuInitialization instance() {
    return INSTANCE == null ? INSTANCE = new InternalGosuInit() : INSTANCE;
  }

  // single module (i.e. runtime)

  public void initializeRuntime( IExecutionEnvironment execEnv, List<? extends GosuPathEntry> pathEntries, String... discretePackages ) {
    ((ExecutionEnvironment)execEnv).initializeDefaultSingleModule( pathEntries, discretePackages );
  }

  public void reinitializeRuntime( IExecutionEnvironment execEnv, List<? extends GosuPathEntry> pathEntries, String... discretePackages ) {
    ((ExecutionEnvironment)execEnv).initializeDefaultSingleModule( pathEntries, discretePackages );
    TypeLoaderAccess.instance().incrementChecksums();
  }

  public void reinitializeSimpleIde( IExecutionEnvironment execEnv, GosucModule module ) {
    ((ExecutionEnvironment)execEnv).initializeSimpleIde( module );
    TypeLoaderAccess.instance().incrementChecksums();
  }

  @Override
  public void uninitializeSimpleIde( IExecutionEnvironment execEnv )
  {
    ((ExecutionEnvironment)execEnv).uninitializeSimpleIde();
  }

  public void initializeSimpleIde( IExecutionEnvironment execEnv, GosucModule module ) {
    ((ExecutionEnvironment)execEnv).initializeSimpleIde( module );
  }

  public void uninitializeRuntime( IExecutionEnvironment execEnv ) {
    ((ExecutionEnvironment)execEnv).uninitializeDefaultSingleModule();
  }

  public void initializeCompiler(IExecutionEnvironment execEnv, GosucModule module) {
    ((ExecutionEnvironment)execEnv).initializeCompiler(module);
  }

  public void uninitializeCompiler( IExecutionEnvironment execEnv ) {
    ((ExecutionEnvironment)execEnv).uninitializeCompiler();
  }

  // multiple modules

  public void initializeMultipleModules( IExecutionEnvironment execEnv, List<? extends IModule> modules ) {
    ((ExecutionEnvironment)execEnv).initializeMultipleModules( modules );
  }

  public void uninitializeMultipleModules( IExecutionEnvironment execEnv ) {
    if (ExecutionMode.isRuntime()) {
      throw new IllegalStateException( "The typesystem is not in multi-module mode." );
    }
    ((ExecutionEnvironment)execEnv).uninitializeMultipleModules();
  }
}
