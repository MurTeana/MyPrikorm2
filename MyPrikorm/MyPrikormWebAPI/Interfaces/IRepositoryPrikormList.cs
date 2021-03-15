using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MyPrikormWebAPI.DB.Entities;

namespace MyPrikormWebAPI.Interfaces
{
    interface IRepositoryPrikormList
    {
        Task<List<PrikormList>> GetAll();
        Task<PrikormList> Get(int id);
        Task<PrikormList> Create(PrikormList prikormlist);
        Task<PrikormList> Update(PrikormList prikormlist);
        Task<PrikormList> Delete(int id);
    }
}
