using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MyPrikormWebAPI.Model.ViewModels
{
    public class PrikormListViewModel
    {
        public int Id { get; set; }
        public int IdUser { get; set; }
        public DateTime DateMeal { get; set; }
        public string IdMeal { get; set; }
        public int IdProduct { get; set; }
        public int Weight { get; set; }
        public string Reaction { get; set; }
    }
}
