/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.computation.qualitygate;

import java.util.Objects;
import org.junit.rules.ExternalResource;

public class MutableQualityGateHolderRule extends ExternalResource implements MutableQualityGateHolder {
  private QualityGate qualityGate;

  @Override
  public void setQualityGate(QualityGate qualityGate) {
    Objects.requireNonNull(qualityGate);
    if (this.qualityGate != null) {
      throw new IllegalStateException("QualityGate can not be set more than once");
    }
    this.qualityGate = qualityGate;
  }

  @Override
  public QualityGate getQualityGate() {
    if (this.qualityGate == null) {
      throw new IllegalStateException("QualityGate has not been set");
    }
    return qualityGate;
  }

  @Override
  protected void after() {
    reset();
  }

  public void reset() {
    this.qualityGate = null;
  }
}
