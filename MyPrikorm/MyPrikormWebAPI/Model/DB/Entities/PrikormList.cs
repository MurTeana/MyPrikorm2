using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MyPrikormWebAPI.Model.DB.Entities
{
    public class PrikormList
    {
        public PrikormList()
        {
            PrikormList_ = new HashSet<PrikormList>();
        }
        [Key]
        public int Id { get; set; }
        [Required]
        public int IdUser { get; set; }
        public DateTime DateMeal { get; set; }
        public string IdMeal { get; set; }
        public int IdProduct { get; set; }
        public int Weight { get; set; }
        public string Reaction { get; set; }


        public virtual User User { get; set; }
        public virtual ICollection<PrikormList> PrikormList_ { get; set; }
    }
}
