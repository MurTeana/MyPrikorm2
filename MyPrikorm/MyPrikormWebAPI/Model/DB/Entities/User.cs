using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MyPrikormWebAPI.Model.DB.Entities
{
    public class User
    {
        public User()
        {
            PrikormList = new HashSet<PrikormList>();
        }
        [Key]
        public int Id { get; set; }
        [Required]
        public string Username { get; set; }
        public string ChildName { get; set; }
        public string Email { get; set; }
        public string Pnoneno { get; set; }
        public string Password { get; set; }

        public virtual ICollection<PrikormList> PrikormList { get; set; }
    }
}
