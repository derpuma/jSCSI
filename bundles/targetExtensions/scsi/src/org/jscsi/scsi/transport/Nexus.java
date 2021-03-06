/**
 * Copyright (c) 2012, University of Konstanz, Distributed Systems Group
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of Konstanz nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
//Cleversafe open-source code header - Version 1.1 - December 1, 2006
//
//Cleversafe Dispersed Storage(TM) is software for secure, private and
//reliable storage of the world's data using information dispersal.
//
//Copyright (C) 2005-2007 Cleversafe, Inc.
//
//This program is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License
//as published by the Free Software Foundation; either version 2
//of the License, or (at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program; if not, write to the Free Software
//Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
//USA.
//
//Contact Information: 
// Cleversafe, 10 W. 35th Street, 16th Floor #84,
// Chicago IL 60616
// email: licensing@cleversafe.org
//
//END-OF-HEADER
//-----------------------
//@author: John Quigley <jquigley@cleversafe.com>
//@date: January 1, 2008
//---------------------

package org.jscsi.scsi.transport;

/**
 * Represents an I_T, I_T_L, or I_T_L_Q Nexus.
 * <p>
 * An I_T Nexus identification will have L and Q set to invalid (negative) values.
 * <p>
 * An I_T_L Nexus identification will have Q set to an invalid (negative) value.
 */
public class Nexus
{
   private String initiatorPortIdentifier;
   private String targetPortIdentifier;

   private long logicalUnitNumber;
   private long taskTag;

   /**
    * Contruct an I_T Nexus identification. L and Q are set to invalid (negative) values.
    * @param initiatorPortIdentifier The initiator port identifier.
    * @param targetPortIdentifier The target port identifier.
    */
   public Nexus(String initiatorPortIdentifier, String targetPortIdentifier)
   {
      super();
      this.initiatorPortIdentifier = initiatorPortIdentifier;
      this.targetPortIdentifier = targetPortIdentifier;
      this.logicalUnitNumber = -1;
      this.taskTag = -1;
   }

   /**
    * Construct an I_T_L Nexus identification. Q is set to an invalid (negative) value.
    * 
    * @param initiatorPortIdentifier The initiator port identifier.
    * @param targetPortIdentifier The target port identifier.
    * @param logicalUnitNumber The Logical Unit Number.
    */
   public Nexus(String initiatorPortIdentifier, String targetPortIdentifier, long logicalUnitNumber)
   {
      super();
      this.initiatorPortIdentifier = initiatorPortIdentifier;
      this.targetPortIdentifier = targetPortIdentifier;
      this.logicalUnitNumber = logicalUnitNumber;
      this.taskTag = -1;
   }

   /**
    * Construct an I_T_L_Q Nexus identification.
    * 
    * @param initiatorPortIdentifier The initiator port identifier.
    * @param targetPortIdentifier The target port identifier.
    * @param logicalUnitNumber The Logical Unit Number.
    * @param taskTag The task tag.
    */
   public Nexus(
         String initiatorPortIdentifier,
         String targetPortIdentifier,
         long logicalUnitNumber,
         long taskTag)
   {
      super();
      this.initiatorPortIdentifier = initiatorPortIdentifier;
      this.targetPortIdentifier = targetPortIdentifier;
      this.logicalUnitNumber = logicalUnitNumber;
      this.taskTag = taskTag;
   }

   /**
    * Construct an I_T_L_Q Nexus identification from an I_T_L Nexus identification and a task tag.
    * A convenience constructor for use with a sequence of commands using shifting task tags on
    * a stable I_T_L Nexus. 
    * 
    * @param nexus An I_T_L Nexus identification.
    * @param taskTag The task tag.
    */
   public Nexus(Nexus nexus, long taskTag)
   {
      assert nexus.logicalUnitNumber > 0 : "I_T_L_Q Nexus should be constructed from I_T_L Nexus";
      this.initiatorPortIdentifier = nexus.initiatorPortIdentifier;
      this.targetPortIdentifier = nexus.targetPortIdentifier;
      this.logicalUnitNumber = nexus.logicalUnitNumber;
      this.taskTag = taskTag;
   }

   /**
    * The Initiator Port Identifier.
    */
   public String getInitiatorPortIdentifier()
   {
      return initiatorPortIdentifier;
   }

   /**
    * The Target Port Identifier.
    */
   public String getTargetPortIdentifier()
   {
      return targetPortIdentifier;
   }

   /**
    * The Logical Unit Number. Negative for I_T Nexus identifiers.
    */
   public long getLogicalUnitNumber()
   {
      return logicalUnitNumber;
   }

   /**
    * The Task Tag. Negative for I_T and I_T_L Nexus identifiers.
    */
   public long getTaskTag()
   {
      return taskTag;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result =
            prime * result
                  + ((initiatorPortIdentifier == null) ? 0 : initiatorPortIdentifier.hashCode());
      result = prime * result + (int) (logicalUnitNumber ^ (logicalUnitNumber >>> 32));
      result =
            prime * result + ((targetPortIdentifier == null) ? 0 : targetPortIdentifier.hashCode());
      result = prime * result + (int) (taskTag ^ (taskTag >>> 32));
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      final Nexus other = (Nexus) obj;
      if (initiatorPortIdentifier == null)
      {
         if (other.initiatorPortIdentifier != null)
            return false;
      }
      else if (!initiatorPortIdentifier.equals(other.initiatorPortIdentifier))
         return false;
      if (logicalUnitNumber != other.logicalUnitNumber)
         return false;
      if (targetPortIdentifier == null)
      {
         if (other.targetPortIdentifier != null)
            return false;
      }
      else if (!targetPortIdentifier.equals(other.targetPortIdentifier))
         return false;
      if (taskTag != other.taskTag)
         return false;
      return true;
   }

   @Override
   public String toString()
   {
      String output = new String();
      if (this.logicalUnitNumber == -1 || this.taskTag == -1)
      {
         output +=
               "<I_T nexus initiatorPort: " + this.initiatorPortIdentifier + " targetPort: "
                     + this.targetPortIdentifier;
      }
      else if (this.taskTag == -1)
      {
         output +=
               "<I_T_L nexus initiatorPort: " + this.initiatorPortIdentifier + " targetPort: "
                     + this.targetPortIdentifier + " LUN: " + this.logicalUnitNumber;
      }
      else
      {
         output +=
               "<I_T_L_Q nexus initiatorPort: " + this.initiatorPortIdentifier + " targetPort: "
                     + this.targetPortIdentifier + " LUN: " + this.logicalUnitNumber + " taskTag: "
                     + this.taskTag;
      }
      output += ">";
      return output;
   }
}
