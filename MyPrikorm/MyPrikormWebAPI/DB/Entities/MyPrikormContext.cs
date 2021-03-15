using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MyPrikormWebAPI.DB.Entities
{
    public partial class MyPrikormContext : DbContext
    {
        #region Constructor
        public MyPrikormContext(DbContextOptions<MyPrikormContext>
        options)
        : base(options)
        { }
        #endregion
        public virtual DbSet<User> User { get; set; }

        protected override void OnModelCreating(ModelBuilder
modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            //modelBuilder.Entity<TransformerSubstation>(entity =>
            //{
            //    entity.HasMany(a => a.SubstationLines).WithOne(a => a.TransformerSubstation).HasForeignKey(a => a.TransformerSubstationId);
            //    entity.HasOne(a => a.Project).WithMany(a => a.TransformerSubstations).HasForeignKey(a => a.ProjectId);
            //    entity.HasOne(a => a.State).WithMany(a => a.TransformerSubstations).HasForeignKey(a => a.StateId);
            //    entity.HasOne(a => a.Builder).WithMany(a => a.TransformerSubstations).HasForeignKey(a => a.BuilderId);
            //});
            //modelBuilder.Entity<TransmissionLine>(entity =>
            //{
            //    entity.HasMany(a => a.SubstationLines).WithOne(a => a.TransmissionLine).HasForeignKey(a => a.TransmissionLineId);
            //    entity.HasOne(a => a.RatedVoltage).WithMany(a => a.TransmissionLines).HasForeignKey(a => a.RatedVoltageId);
            //    entity.HasOne(a => a.Topology).WithMany(a => a.TransmissionLines).HasForeignKey(a => a.TopologyId);
            //    entity.HasOne(a => a.TypeOfCurrent).WithMany(a => a.TransmissionLines).HasForeignKey(a => a.TypeOfCurrentId);
            //    entity.HasOne(a => a.ParallelCircuits).WithMany(a => a.TransmissionLines).HasForeignKey(a => a.ParallelCircuitsId);
            //    entity.HasOne(a => a.FunctionalPurpose).WithMany(a => a.TransmissionLines).HasForeignKey(a => a.FunctionalPurposeId);
            //});
            //modelBuilder.Entity<SubstationLines>(entity =>
            //{
            //    entity.HasOne(a => a.TransmissionLine).WithMany(a => a.SubstationLines).HasForeignKey(a => a.TransmissionLineId);
            //    entity.HasOne(a => a.TransformerSubstation).WithMany(a => a.SubstationLines).HasForeignKey(a => a.TransformerSubstationId);
            //});
        }
    }
}
