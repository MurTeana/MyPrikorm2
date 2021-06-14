using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;


namespace MyPrikormWebAPI.Model.DB.Entities
{
    public class Meal
    {
        public Meal()
        {
            PrikormList = new HashSet<PrikormList>();
        }
        [Key]
        public int Id { get; set; }
        [Required]
        public string Meal_ { get; set; }

        public virtual ICollection<PrikormList> PrikormList { get; set; }
    }
}
